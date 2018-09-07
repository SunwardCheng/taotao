package com.taotao.rest.jedis;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	
	/**
	 * 单机版测试
	* @Title: testJedisPool 
	* @Description: TODO 
	* @param     
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void testJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		//创建Jedis连接池
		JedisPool pool = new JedisPool(config,"192.168.140.143",6379,100000,"123456");
		
		//从连接池中获取Jedis对象
		Jedis jedis = pool.getResource();
		String string = jedis.get("a");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群版测试
	* @Title: testJedisCluster 
	* @Description: TODO 
	* @param     
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void testJedisCluster(){
		HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.140.143", 7001));
		nodes.add(new HostAndPort("192.168.140.143", 7002));
		nodes.add(new HostAndPort("192.168.140.143", 7003));
		nodes.add(new HostAndPort("192.168.140.143", 7004));
		nodes.add(new HostAndPort("192.168.140.143", 7005));
		nodes.add(new HostAndPort("192.168.140.143", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		
		cluster.set("key1", "1000");
		System.out.println(cluster.get("key1"));
		
		try {
			cluster.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
