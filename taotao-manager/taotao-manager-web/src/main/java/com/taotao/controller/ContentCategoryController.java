package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/content/category"})
public class ContentCategoryController
{
  @Autowired
  private ContentCategoryService contentCategoryService;
  
  @RequestMapping({"/list"})
  @ResponseBody
  public List<EUTreeNode> getContentCateList(@RequestParam(value="id", defaultValue="0") Long parentId)
  {
    List<EUTreeNode> list = this.contentCategoryService.getCategoryList(parentId.longValue());
    return list;
  }
  
  @RequestMapping({"/create"})
  @ResponseBody
  public TaotaoResult createContentCategory(Long parentId, String name)
  {
    TaotaoResult result = this.contentCategoryService.insertContentCategory(parentId.longValue(), name);
    return result;
  }
  
  @RequestMapping({"/delete"})
  @ResponseBody
  public TaotaoResult deleteContentCategory(Long id)
    throws Exception
  {
    TaotaoResult result = this.contentCategoryService.deleteContentCategory(id.longValue());
    return result;
  }
  
  @RequestMapping({"/update"})
  @ResponseBody
  public TaotaoResult updateContentCategory(long id, String name)
  {
    TaotaoResult result = this.contentCategoryService.updateContentCategory(id, name);
    return result;
  }
}
