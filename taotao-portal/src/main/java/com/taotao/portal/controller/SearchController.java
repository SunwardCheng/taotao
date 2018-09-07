package com.taotao.portal.controller;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController
{
  @Autowired
  private SearchService searchService;
  
  @RequestMapping(value={"/search"}, produces={"text/html;charset=utf-8"})
  public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue="1") Integer page, Model model)
  {
    if (queryString != null) {
      try
      {
        queryString = new String(queryString.getBytes("iso8859-1"), "UTF-8");
      }
      catch (UnsupportedEncodingException e)
      {
        e.printStackTrace();
      }
    }
    SearchResult result = this.searchService.search(queryString, page.intValue());
    if (result != null)
    {
      model.addAttribute("query", queryString);
      model.addAttribute("totalPages", Long.valueOf(result.getPageCount()));
      model.addAttribute("itemList", result.getItemList());
      model.addAttribute("page", Long.valueOf(result.getCurPage()));
    }
    return "search";
  }
}
