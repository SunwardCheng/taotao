package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/content"})
public class ContentController
{
  @Autowired
  private ContentService contentService;
  
  @RequestMapping({"/query/list"})
  @ResponseBody
  public EUDataGridResult getContentList(int page, int rows, long categoryId)
  {
    EUDataGridResult result = this.contentService.getContentList(page, rows, Long.valueOf(categoryId));
    
    return result;
  }
  
  @RequestMapping({"/save"})
  @ResponseBody
  public TaotaoResult insertContent(TbContent content)
  {
    TaotaoResult result = this.contentService.insertContent(content);
    return result;
  }
  
  @RequestMapping({"/edit"})
  @ResponseBody
  public TaotaoResult uddateContent(TbContent content)
  {
    TaotaoResult result = this.contentService.updateContent(content);
    return result;
  }
  
  @RequestMapping({"/delete"})
  @ResponseBody
  public TaotaoResult deleteContent(@RequestParam("ids") Long id)
  {
    TaotaoResult result = this.contentService.deleteContent(id);
    return result;
  }
}
