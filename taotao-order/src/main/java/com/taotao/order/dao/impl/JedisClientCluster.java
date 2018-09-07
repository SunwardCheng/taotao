package com.taotao.order.dao.impl;

import com.taotao.order.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class JedisClientCluster
  implements JedisClient
{
  @Autowired
  private JedisCluster JedisCluster;
  
  public String get(String key)
  {
    return this.JedisCluster.get(key);
  }
  
  public String set(String key, String value)
  {
    return this.JedisCluster.set(key, value);
  }
  
  public String hget(String hkey, String key)
  {
    return this.JedisCluster.hget(hkey, key);
  }
  
  public long hset(String hkey, String key, String value)
  {
    return this.JedisCluster.hset(hkey, key, value).longValue();
  }
  
  public long incr(String key)
  {
    return this.JedisCluster.incr(key).longValue();
  }
  
  public long expire(String key, int seconds)
  {
    return this.JedisCluster.expire(key, seconds).longValue();
  }
  
  public long ttl(String key)
  {
    return this.JedisCluster.ttl(key).longValue();
  }
  
  public long del(String key)
  {
    return this.JedisCluster.del(key).longValue();
  }
  
  public long hdel(String hkey, String key)
  {
    return this.JedisCluster.hdel(hkey, new String[] { key }).longValue();
  }
}
