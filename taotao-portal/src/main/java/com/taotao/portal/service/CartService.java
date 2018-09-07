package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface CartService
{
  public abstract TaotaoResult addCartItem(long paramLong, int paramInt, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
  
  public abstract List<CartItem> getCartItemList(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
  
  public abstract TaotaoResult updateCartItem(long paramLong, int paramInt, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
  
  public abstract TaotaoResult deleteCartItem(long paramLong, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}
