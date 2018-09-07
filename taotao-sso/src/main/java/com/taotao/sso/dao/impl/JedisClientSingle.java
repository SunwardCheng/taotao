package com.taotao.sso.dao.impl;

import com.taotao.sso.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle
  implements JedisClient
{
  @Autowired
  private JedisPool jedisPool;
  @Value("${redisSingle.auth}")
  private String password;
  
  public String get(String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    String string = jedis.get(key);
    jedis.close();
    return string;
  }
  
  public String set(String key, String value)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    String string = jedis.set(key, value);
    jedis.close();
    return string;
  }
  
  public String hget(String hkey, String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    String string = jedis.hget(hkey, key);
    jedis.close();
    return string;
  }
  
  public long hset(String hkey, String key, String value)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    Long result = jedis.hset(hkey, key, value);
    jedis.close();
    return result.longValue();
  }
  
  public long incr(String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    long result = jedis.incr(key).longValue();
    jedis.close();
    return result;
  }
  
  public long expire(String key, int seconds)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    long result = jedis.expire(key, seconds).longValue();
    jedis.close();
    return result;
  }
  
  public long ttl(String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    long result = jedis.ttl(key).longValue();
    jedis.close();
    return result;
  }
  
  public long del(String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    long result = jedis.del(key).longValue();
    jedis.close();
    return result;
  }
  
  public long hdel(String hkey, String key)
  {
    Jedis jedis = this.jedisPool.getResource();
    jedis.auth(this.password);
    long result = jedis.del(new String[] { hkey, key }).longValue();
    jedis.close();
    return result;
  }
}
