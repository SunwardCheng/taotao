package com.taotao.portal.pojo;

public class Item
{
  private String id;
  private String title;
  private String sell_point;
  private long price;
  private String image;
  private String category_name;
  private String item_des;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
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
  
  public String getSell_point()
  {
    return this.sell_point;
  }
  
  public void setSell_point(String sell_point)
  {
    this.sell_point = sell_point;
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
  
  public String getCategory_name()
  {
    return this.category_name;
  }
  
  public void setCategory_name(String category_name)
  {
    this.category_name = category_name;
  }
  
  public String getItem_des()
  {
    return this.item_des;
  }
  
  public void setItem_des(String item_des)
  {
    this.item_des = item_des;
  }
  
  public String[] getImages()
  {
    if (this.image != null)
    {
      String[] images = this.image.split(",");
      return images;
    }
    return null;
  }
}
