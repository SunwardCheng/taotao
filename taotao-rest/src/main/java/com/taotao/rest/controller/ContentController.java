package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({"/content"})
@Controller
public class ContentController
{
  @Autowired
  private ContentService contentService;
  
  @RequestMapping({"/list/{contentCategoryId}"})
  @ResponseBody
  public TaotaoResult getContentList(@PathVariable long contentCategoryId)
  {
    try
    {
      List<TbContent> list = this.contentService.getContentList(contentCategoryId);
      return TaotaoResult.ok(list);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
  }
}
