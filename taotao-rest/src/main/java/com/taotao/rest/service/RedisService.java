package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public abstract interface RedisService
{
  public abstract TaotaoResult synContent(long paramLong);
}
