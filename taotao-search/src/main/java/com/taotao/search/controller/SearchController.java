package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import java.io.PrintStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController
{
  @Autowired
  private SearchService searchService;
  
  @RequestMapping(value={"/query"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public TaotaoResult serarch(@RequestParam("q") String queryString, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="60") Integer rows)
  {
    if (StringUtils.isBlank(queryString)) {
      return TaotaoResult.build(Integer.valueOf(400), "查询条件不能为空");
    }
    SearchResult searchResult = null;
    try
    {
      queryString = new String(queryString.getBytes("iso8859-1"), "UTF-8");
      System.out.println(queryString + "###########*********************################&&&&&&&&&&&&&&&&$$$$$$$$$$$$$");
      searchResult = this.searchService.search(queryString, page.intValue(), rows.intValue());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
    return TaotaoResult.ok(searchResult);
  }
}
