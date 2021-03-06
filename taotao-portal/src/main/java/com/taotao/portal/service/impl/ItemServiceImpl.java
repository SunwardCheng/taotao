package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl
  implements ItemService
{
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  @Value("${ITEM_INFO_URL}")
  private String ITEM_INFO_URL;
  @Value("${ITEM_DESC_URL}")
  private String ITEM_DESC_URL;
  @Value("${ITEM_PARAM_URL}")
  private String ITEM_PARAM_URL;
  
  public ItemInfo getItemById(long itemId)
  {
    try
    {
      String json = HttpClientUtil.doGet(this.REST_BASE_URL + this.ITEM_INFO_URL + itemId);
      if (!StringUtils.isBlank(json))
      {
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
        if ((taotaoResult != null) && (taotaoResult.getStatus().intValue() == 200)) {
          return (ItemInfo)taotaoResult.getData();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public String getItemDescById(Long itemId)
  {
    try
    {
      String json = HttpClientUtil.doGet(this.REST_BASE_URL + this.ITEM_DESC_URL + itemId);
      
      TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
      if (taotaoResult.getStatus().intValue() == 200)
      {
        TbItemDesc itemDesc = (TbItemDesc)taotaoResult.getData();
        
        return itemDesc.getItemDesc();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public String getItemParam(Long itemId)
  {
    try
    {
      String json = HttpClientUtil.doGet(this.REST_BASE_URL + this.ITEM_PARAM_URL + itemId);
      
      TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
      if (taotaoResult.getStatus().intValue() == 200)
      {
        TbItemParamItem itemParamItem = (TbItemParamItem)taotaoResult.getData();
        
        String paramData = itemParamItem.getParamData();
        


        List<Map> jsonToList = JsonUtils.jsonToList(paramData, Map.class);
        
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map map1 : jsonToList)
        {
          sb.append("        <tr>\n");
          sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + map1.get("group") + "</th>\n");
          sb.append("        </tr>\n");
          
          List<Map> list2 = (List)map1.get("params");
          for (Map map2 : list2)
          {
            sb.append("        <tr>\n");
            sb.append("            <td>" + map2.get("k") + "</th>\n");
            sb.append("            <td>" + map2.get("v") + "</td>\n");
            sb.append("        </tr>\n");
          }
        }
        sb.append("    </tbody>\n");
        sb.append("</table");
        

        return sb.toString();
      }
    }
    catch (Exception localException) {}
    return "";
  }
}
