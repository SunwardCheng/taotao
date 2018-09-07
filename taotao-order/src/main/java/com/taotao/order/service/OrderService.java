package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import java.util.List;

public abstract interface OrderService
{
  public abstract TaotaoResult createOrder(TbOrder paramTbOrder, List<TbOrderItem> paramList, TbOrderShipping paramTbOrderShipping);
}
