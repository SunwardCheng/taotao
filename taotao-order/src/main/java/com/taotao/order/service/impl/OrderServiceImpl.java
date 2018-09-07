package com.taotao.order.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl
  implements OrderService
{
  @Autowired
  private TbOrderMapper orderMapper;
  @Autowired
  private TbOrderItemMapper orderItemMapper;
  @Autowired
  private TbOrderShippingMapper orderShippingMapper;
  @Autowired
  private JedisClient jedisClient;
  @Value("${ORDER_GEN_KEY}")
  private String ORDER_GEN_KEY;
  @Value("${ORDER_INIT_ID}")
  private String ORDER_INIT_ID;
  @Value("${ORDER_DETAIL_GEN_KEY}")
  private String ORDER_DETAIL_GEN_KEY;
  
  public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping)
  {
    String sting = this.jedisClient.get(this.ORDER_GEN_KEY);
    if (StringUtils.isBlank(sting)) {
      this.jedisClient.set(this.ORDER_GEN_KEY, this.ORDER_INIT_ID);
    }
    long orderId = this.jedisClient.incr(this.ORDER_GEN_KEY);
    
    order.setOrderId(orderId + "");
    
    order.setStatus(Integer.valueOf(1));
    Date date = new Date();
    order.setCreateTime(date);
    order.setUpdateTime(date);
    
    order.setBuyerRate(Integer.valueOf(0));
    
    this.orderMapper.insert(order);
    for (TbOrderItem tbOrderItem : itemList)
    {
      long orderDetialId = this.jedisClient.incr(this.ORDER_DETAIL_GEN_KEY);
      tbOrderItem.setId(orderDetialId + "");
      tbOrderItem.setOrderId(orderId + "");
      
      this.orderItemMapper.insert(tbOrderItem);
    }
    orderShipping.setOrderId(orderId + "");
    orderShipping.setCreated(date);
    orderShipping.setUpdated(date);
    this.orderShippingMapper.insert(orderShipping);
    
    return TaotaoResult.ok(Long.valueOf(orderId));
  }
}
