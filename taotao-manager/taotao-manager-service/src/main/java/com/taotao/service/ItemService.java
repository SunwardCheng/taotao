package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public abstract interface ItemService
{
  public abstract TbItem getItemById(long paramLong);
  
  public abstract EUDataGridResult getItemList(int paramInt1, int paramInt2);
  
  public abstract TaotaoResult createItem(TbItem paramTbItem, String paramString1, String paramString2)
    throws Exception;
}
