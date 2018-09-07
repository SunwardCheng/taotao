package com.taotao.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamItemServiceImpl
  implements ItemParamItemService
{
  @Autowired
  private TbItemParamItemMapper itemParamItemMapper;
  
  public String getItemParamItemByItemId(Long itemId)
  {
    TbItemParamItemExample example = new TbItemParamItemExample();
    TbItemParamItemExample.Criteria criteria = example.createCriteria();
    criteria.andItemIdEqualTo(itemId);
    

    List<TbItemParamItem> list = this.itemParamItemMapper.selectByExampleWithBLOBs(example);
    if ((list == null) || (list.size() == 0)) {
      return "";
    }
    TbItemParamItem itemParamItem = (TbItemParamItem)list.get(0);
    String itemParam = itemParamItem.getParamData();
    

    List<Map> jsonToList = JsonUtils.jsonToList(itemParam, Map.class);
    System.out.println(itemParam);
    
    StringBuffer sb = new StringBuffer();
    sb.append("<table class=\"tm-tableAttr\">\n");
    sb.append("    <tbody>\n");
    for (Map map1 : jsonToList)
    {
      sb.append("        <tr class=\"tm-tableAttrSub\">\n");
      sb.append("            <th colspan=\"2\">" + map1.get("group") + "</th>\n");
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
