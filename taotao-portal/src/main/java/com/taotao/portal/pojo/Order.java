package com.taotao.portal.pojo;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import java.util.List;

public class Order
  extends TbOrder
{
  private List<TbOrderItem> orderItems;
  private TbOrderShipping orderShipping;
  
  public List<TbOrderItem> getOrderItems()
  {
    return this.orderItems;
  }
  
  public void setOrderItems(List<TbOrderItem> orderItems)
  {
    this.orderItems = orderItems;
  }
  
  public TbOrderShipping getOrderShipping()
  {
    return this.orderShipping;
  }
  
  public void setOrderShipping(TbOrderShipping orderShipping)
  {
    this.orderShipping = orderShipping;
  }
}
