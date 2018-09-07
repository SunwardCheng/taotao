package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 
* <p>Title: CatNode</p>
* <p>Description: 分类节点</p>
* <p>Company: 聚识科技</p> 
* @author Sunward
* @date 2018年8月6日 下午5:32:21
 */
public class CatNode
{
	
	/**
	 * 标注在转换成Json之后的key名
	 */
  @JsonProperty("n")
  private String name;
  @JsonProperty("u")
  private String url;
  @JsonProperty("i")
  private List<?> item;
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public void setUrl(String url)
  {
    this.url = url;
  }
  
  public List<?> getItem()
  {
    return this.item;
  }
  
  public void setItem(List<?> item)
  {
    this.item = item;
  }
}
