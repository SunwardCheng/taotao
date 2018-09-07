package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController
{
  @Autowired
  private ItemCatService itemCatService;
  
  
  
  @RequestMapping({"/itemcat/list"})
  @ResponseBody
  public Object getItemCatList(String callback)
  {
    CatResult catResult = this.itemCatService.getItemCatList();
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
    /**
     * 包装成回调函数，参数是catResult,4.1之后
     */
    mappingJacksonValue.setJsonpFunction(callback);
    
    return mappingJacksonValue;
  }
  
 /* @RequestMapping(value ="/itemcat/list", 
		  produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
  @ResponseBody
  public String getItemCatList(String callback){
	  CatResult catResult = itemCatService.getItemCatList();
	  String json = JsonUtils.objectToJson(catResult);
	  String result = callback+"(" + json +")";
	  return result;
  }*/
  
}
