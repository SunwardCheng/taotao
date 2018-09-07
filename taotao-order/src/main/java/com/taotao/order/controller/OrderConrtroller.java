package com.taotao.order.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderConrtroller
{
  @Autowired
  private OrderService orderService;
  
  @RequestMapping(value={"/create"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public TaotaoResult createOrder(@RequestBody Order order)
  {
    try
    {
      return this.orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
  }
}
