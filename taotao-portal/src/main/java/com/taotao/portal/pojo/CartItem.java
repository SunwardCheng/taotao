package com.taotao.portal.pojo;

public class CartItem
{
  private long id;
  private String title;
  private Integer num;
  private long price;
  private String image;
  
  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public Integer getNum()
  {
    return this.num;
  }
  
  public void setNum(Integer num)
  {
    this.num = num;
  }
  
  public long getPrice()
  {
    return this.price;
  }
  
  public void setPrice(long price)
  {
    this.price = price;
  }
  
  public String getImage()
  {
    return this.image;
  }
  
  public void setImage(String image)
  {
    this.image = image;
  }
}
