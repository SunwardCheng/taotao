package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import java.io.PrintStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/item/param"})
public class ItemParamController
{
  @Autowired
  private ItemParamService itemParamService;
  
  @RequestMapping({"/query/itemcatid/{itemCatId}"})
  @ResponseBody
  public TaotaoResult getItemParamByCId(@PathVariable long itemCatId)
  {
    TaotaoResult result = this.itemParamService.getItemParamById(itemCatId);
    System.out.println("怎么现在又不行了呢");
    System.err.println(JsonUtils.objectToJson(result));
    return result;
  }
  
  @RequestMapping({"/save/{cid}"})
  @ResponseBody
  public TaotaoResult insertItemParam(@PathVariable long cid, String paramData)
  {
    TbItemParam itemParam = new TbItemParam();
    itemParam.setItemCatId(Long.valueOf(cid));
    itemParam.setParamData(paramData);
    TaotaoResult result = this.itemParamService.insertItemParam(itemParam);
    return result;
  }
  
  @RequestMapping({"/list"})
  @ResponseBody
  public EUDataGridResult getItemParamList(int page, int rows)
    throws Exception
  {
    EUDataGridResult result = this.itemParamService.getItemParamList(page, rows);
    return result;
  }
}
