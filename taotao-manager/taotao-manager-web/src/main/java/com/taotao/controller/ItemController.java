package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController
{
  @Autowired
  private ItemService itemService;
  
  @RequestMapping({"/item/{itemId}"})
  @ResponseBody
  public TbItem getItemById(@PathVariable long itemId)
  {
    TbItem item = this.itemService.getItemById(itemId);
    return item;
  }
  
  @RequestMapping({"/item/list"})
  @ResponseBody
  public EUDataGridResult getItemList(Integer page, Integer rows)
  {
    EUDataGridResult result = this.itemService.getItemList(page.intValue(), rows.intValue());
    return result;
  }
  
  @RequestMapping(value={"/item/save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public TaotaoResult createItem(TbItem item, String desc, String itemParams)
    throws Exception
  {
    TaotaoResult result = this.itemService.createItem(item, desc, itemParams);
    return result;
  }
}
