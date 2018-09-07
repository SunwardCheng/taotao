package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public abstract interface ContentService
{
  public abstract EUDataGridResult getContentList(int paramInt1, int paramInt2, Long paramLong);
  
  public abstract TaotaoResult insertContent(TbContent paramTbContent);
  
  public abstract TaotaoResult updateContent(TbContent paramTbContent);
  
  public abstract TaotaoResult deleteContent(Long paramLong);
}
