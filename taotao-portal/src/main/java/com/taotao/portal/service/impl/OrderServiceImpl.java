package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl
  implements OrderService
{
  @Value("${ORDER_BASE_URL}")
  private String ORDER_BASE_URL;
  @Value("${ORDER_CREATE_URL}")
  private String ORDER_CREATE_URL;
  
  public String createOreder(Order order)
  {
    String json = HttpClientUtil.doPostJson(this.ORDER_BASE_URL + this.ORDER_CREATE_URL, JsonUtils.objectToJson(order));
    
    TaotaoResult result = TaotaoResult.format(json);
    if (result.getStatus().intValue() == 200)
    {
      Object orderId = result.getData();
      return orderId.toString();
    }
    return "";
  }
}
