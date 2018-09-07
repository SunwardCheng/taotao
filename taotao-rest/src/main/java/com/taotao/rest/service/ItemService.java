package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public abstract interface ItemService
{
  public abstract TaotaoResult getItemBaseInfo(long paramLong);
  
  public abstract TaotaoResult getItemDesc(long paramLong);
  
  public abstract TaotaoResult getItemParam(long paramLong);
}
