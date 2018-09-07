package com.taotao.portal.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	/**
	 * 执行get请求不带参数
	* @Title: doGet 
	* @Description: TODO 
	* @param @throws Exception    
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void doGet() throws Exception{
		//创建一个Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Get对象
		HttpGet get = new HttpGet("http://www.baidu.com");
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String request = EntityUtils.toString(entity,"utf-8");
		System.out.println(request);
		
		response.close();
		httpClient.close();
		
	}
	
	/**
	 * get带参数
	* @Title: doGetWithParams 
	* @Description: TODO 
	* @param @throws Exception    
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void doGetWithParams() throws Exception{
		//创建一个Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个URI对象
		URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s");
		uriBuilder.addParameter("wd", "http");
		//创建一个Get对象
		HttpGet get = new HttpGet(uriBuilder.build());
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String request = EntityUtils.toString(entity,"utf-8");
		System.out.println(request);
		
		response.close();
		httpClient.close();
	}
	
	/**
	 * Post请求不带参数
	* @Title: doPost 
	* @Description: TODO 
	* @param @throws Exception    
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void doPost() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println(string);
		response.close();
		httpClient.close();
	}
	
	/**
	 * Post带参数
	* @Title: doPostWithParams 
	* @Description: TODO 
	* @param @throws Exception    
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void doPostWithParams() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		//创建一个Entity模拟表单
		List<NameValuePair> kvList = new ArrayList<NameValuePair>();
		kvList.add(new BasicNameValuePair("username", "Jack"));
		kvList.add(new BasicNameValuePair("passward", "slow fuck"));
		//包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList);
		post.setEntity(entity);
		
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println(string);
		response.close();
		httpClient.close();
	}
}
