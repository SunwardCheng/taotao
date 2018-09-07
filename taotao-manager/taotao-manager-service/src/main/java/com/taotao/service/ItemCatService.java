package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import java.util.List;

public abstract interface ItemCatService
{
  public abstract List<EUTreeNode> getItemCatList(Long paramLong)
    throws Exception;
  
  public abstract String getItemCatName(Long paramLong);
}
