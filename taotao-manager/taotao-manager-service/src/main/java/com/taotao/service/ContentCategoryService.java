package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import java.util.List;

public abstract interface ContentCategoryService
{
  public abstract List<EUTreeNode> getCategoryList(long paramLong);
  
  public abstract TaotaoResult insertContentCategory(long paramLong, String paramString);
  
  public abstract TaotaoResult deleteContentCategory(long paramLong)
    throws Exception;
  
  public abstract TaotaoResult updateContentCategory(long paramLong, String paramString);
}
