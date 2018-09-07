package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl
  implements RedisService
{
  @Value("${INDEX_CONENT_REDIS_KEY}")
  private String INDEX_CONENT_REDIS_KEY;
  @Autowired
  private JedisClient jedisClient;
  
  public TaotaoResult synContent(long contentCid)
  {
    try
    {
      this.jedisClient.hdel(this.INDEX_CONENT_REDIS_KEY, contentCid + "");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
    return TaotaoResult.ok();
  }
}
