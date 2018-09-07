package com.taotao.rest.service;

import com.taotao.pojo.TbContent;
import java.util.List;

public abstract interface ContentService
{
  public abstract List<TbContent> getContentList(long paramLong);
}
