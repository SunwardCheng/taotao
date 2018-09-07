package com.taotao.portal.controller;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController
{
  @Autowired
  private ItemService itemService;
  
  @RequestMapping({"/item/{itemId}"})
  public String showItem(@PathVariable Long itemId, Model model)
  {
    ItemInfo ItemInfo = this.itemService.getItemById(itemId.longValue());
    model.addAttribute("item", ItemInfo);
    
    return "item";
  }
  
  @RequestMapping(value={"/item/desc/{itemId}"}, produces={"text/html;charset=utf-8"})
  @ResponseBody
  public String getItemDesc(@PathVariable Long itemId)
  {
    String string = this.itemService.getItemDescById(itemId);
    return string;
  }
  
  @RequestMapping(value={"/item/param/{itemId}"}, produces={"text/html;charset=utf-8"})
  @ResponseBody
  public String getItemparam(@PathVariable Long itemId)
  {
    String string = this.itemService.getItemParam(itemId);
    return string;
  }
}
