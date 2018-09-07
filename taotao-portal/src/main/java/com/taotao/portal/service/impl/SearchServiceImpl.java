package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl
  implements SearchService
{
  @Value("${SEARCH_BASE_URL}")
  private String SEARCH_BASE_URL;
  
  public SearchResult search(String queryString, int page)
  {
    HashMap<String, String> param = new HashMap();
    param.put("q", queryString);
    param.put("page", page + "");
    try
    {
      String result = HttpClientUtil.doGet(this.SEARCH_BASE_URL, param);
      
      TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
      if ((taotaoResult != null) && (taotaoResult.getStatus().intValue() == 200)) {
        return (SearchResult)taotaoResult.getData();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
