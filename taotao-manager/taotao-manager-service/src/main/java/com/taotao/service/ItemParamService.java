package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public abstract interface ItemParamService
{
  public abstract TaotaoResult getItemParamById(long paramLong);
  
  public abstract TaotaoResult insertItemParam(TbItemParam paramTbItemParam);
  
  public abstract EUDataGridResult getItemParamList(int paramInt1, int paramInt2)
    throws Exception;
}
