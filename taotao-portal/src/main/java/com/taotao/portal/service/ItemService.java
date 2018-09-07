package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public abstract interface ItemService
{
  public abstract ItemInfo getItemById(long paramLong);
  
  public abstract String getItemDescById(Long paramLong);
  
  public abstract String getItemParam(Long paramLong);
}
