package com.taotao.rest.dao;

public abstract interface JedisClient
{
  public abstract String get(String paramString);
  
  /**
   * 一个key
  * @Title: set 
  * @Description: TODO 
  * @param @param paramString1
  * @param @param paramString2
  * @param @return    
  * @return String    返回类型 
  * @throws
   */
  public abstract String set(String paramString1, String paramString2);
  
  /**
   * 两个key，相当于二级目录，容易分类
  * @Title: hget 
  * @Description: TODO 
  * @param @param paramString1 hashKey
  * @param @param paramString2 普通key
  * @param @return    
  * @return String    返回类型 
  * @throws
   */
  public abstract String hget(String paramString1, String paramString2);
  
  public abstract long hset(String paramString1, String paramString2, String paramString3);
  
  /**
   * 自增长
  * @Title: incr 
  * @Description: TODO 
  * @param @param paramString
  * @param @return    
  * @return long    返回类型 
  * @throws
   */
  public abstract long incr(String paramString);
  
  /**
   * 设置生存时间
  * @Title: expire 
  * @Description: TODO 
  * @param @param paramString
  * @param @param paramInt
  * @param @return    
  * @return long    返回类型 
  * @throws
   */
  public abstract long expire(String paramString, int paramInt);
  
  /**
   * 获取生存时间
  * @Title: ttl 
  * @Description: TODO 
  * @param @param paramString
  * @param @return    
  * @return long    返回类型 
  * @throws
   */
  public abstract long ttl(String paramString);
  
  public abstract long del(String paramString);
  
  public abstract long hdel(String paramString1, String paramString2);
}
