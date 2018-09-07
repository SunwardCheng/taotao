package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl
  implements ContentService
{
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  @Value("${REST_INDEX_AD_URL}")
  private String REST_INDEX_AD_URL;
  
  public String getContentList()
  {
    String result = HttpClientUtil.doGet(this.REST_BASE_URL + this.REST_INDEX_AD_URL);
    try
    {
      TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
      
      List<TbContent> list = (List)taotaoResult.getData();
      
      List<Map> resultList = new ArrayList();
      for (TbContent content : list)
      {
        Map map = new HashMap();
        map.put("src", content.getPic());
        map.put("height", Integer.valueOf(240));
        map.put("width", Integer.valueOf(670));
        map.put("srcB", content.getPic2());
        map.put("widthB", Integer.valueOf(550));
        map.put("heightB", Integer.valueOf(240));
        map.put("herf", content.getUrl());
        map.put("alt", content.getSubTitle());
        resultList.add(map);
      }
      System.out.println(JsonUtils.objectToJson(resultList));
      return JsonUtils.objectToJson(resultList);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
