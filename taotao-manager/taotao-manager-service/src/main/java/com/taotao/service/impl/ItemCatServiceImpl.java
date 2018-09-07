package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCatServiceImpl
  implements ItemCatService
{
  @Autowired
  private TbItemCatMapper itemCatMapper;
  
  public List<EUTreeNode> getItemCatList(Long parentId)
    throws Exception
  {
    TbItemCatExample example = new TbItemCatExample();
    
    TbItemCatExample.Criteria criteria = example.createCriteria();
    
    criteria.andParentIdEqualTo(parentId);
    
    List<TbItemCat> list = this.itemCatMapper.selectByExample(example);
    List<EUTreeNode> treeNodes = new ArrayList();
    for (TbItemCat tbItemCat : list)
    {
      EUTreeNode node = new EUTreeNode();
      node.setId(tbItemCat.getId().longValue());
      node.setState(tbItemCat.getIsParent().booleanValue() ? "closed" : "open");
      node.setText(tbItemCat.getName());
      treeNodes.add(node);
    }
    return treeNodes;
  }
  
  public String getItemCatName(Long itemCatId)
  {
    TbItemCatExample example = new TbItemCatExample();
    
    TbItemCatExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(itemCatId);
    List<TbItemCat> list = this.itemCatMapper.selectByExample(example);
    if ((list != null) && (list.size() > 0))
    {
      String name = ((TbItemCat)list.get(0)).getName();
      return name;
    }
    return null;
  }
}
