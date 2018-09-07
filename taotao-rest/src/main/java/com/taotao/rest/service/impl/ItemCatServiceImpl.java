package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl
  implements ItemCatService
{
  @Autowired
  private TbItemCatMapper itemCatMapper;
  
  public CatResult getItemCatList()
  {
    CatResult catResult = new CatResult();
    
    catResult.setData(getCatList(0L));
    
    return catResult;
  }
  
  private List<?> getCatList(long parentId)
  {
    int count = 0;
    
    TbItemCatExample example = new TbItemCatExample();
    TbItemCatExample.Criteria criteria = example.createCriteria();
    
    criteria.andParentIdEqualTo(Long.valueOf(parentId));
    List<TbItemCat> list = this.itemCatMapper.selectByExample(example);
    
    List resultList = new ArrayList();
    for (TbItemCat itemCat : list) {
      if (itemCat.getIsParent().booleanValue())
      {
        CatNode catNode = new CatNode();
        if (parentId == 0L) {
          catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
        } else {
        	
          catNode.setName(itemCat.getName());
        }
        catNode.setUrl("/products/" + itemCat.getId() + ".html");
        
        catNode.setItem(getCatList(itemCat.getId().longValue()));
        resultList.add(catNode);
        count++;
        if ((parentId == 0L) && (count >= 14)) {
          break;
        }
      }
      else
      {
        resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
      }
    }
    return resultList;
  }
}
