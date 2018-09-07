package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/item"})
public class ItemController
{
  @Autowired
  private ItemService itemService;
  
  @RequestMapping({"/info/{itemId}"})
  @ResponseBody
  public TaotaoResult getItemBaseInfo(@PathVariable Long itemId)
  {
    TaotaoResult result = this.itemService.getItemBaseInfo(itemId.longValue());
    return result;
  }
  
  @RequestMapping({"/desc/{itemId}"})
  @ResponseBody
  public TaotaoResult getItemDesc(@PathVariable Long itemId)
  {
    TaotaoResult result = this.itemService.getItemDesc(itemId.longValue());
    return result;
  }
  
  @RequestMapping({"/param/{itemId}"})
  @ResponseBody
  public TaotaoResult getItemParam(@PathVariable Long itemId)
  {
    TaotaoResult result = this.itemService.getItemParam(itemId.longValue());
    return result;
  }
}
