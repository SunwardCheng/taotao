package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl
  implements CartService
{
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  @Value("${ITEM_INFO_URL}")
  private String ITEM_INFO_URL;
  
  public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response)
  {
    CartItem cartItem = null;
    
    List<CartItem> itemList = getCatItemList(request);
    if ((itemList != null) && (itemList.size() > 0)) {
      for (CartItem cItem : itemList) {
        if (cItem.getId() == itemId)
        {
          cItem.setNum(Integer.valueOf(cItem.getNum().intValue() + num));
          cartItem = cItem;
          break;
        }
      }
    }
    if (cartItem == null)
    {
      cartItem = new CartItem();
      
      String json = HttpClientUtil.doGet(this.REST_BASE_URL + this.ITEM_INFO_URL + itemId);
      
      TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
      if (result.getStatus().intValue() == 200)
      {
        TbItem item = (TbItem)result.getData();
        cartItem.setId(item.getId().longValue());
        cartItem.setTitle(item.getTitle());
        cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
        cartItem.setNum(Integer.valueOf(num));
        cartItem.setPrice(item.getPrice().longValue());
      }
      itemList.add(cartItem);
    }
    CookieUtils.setCookie(request, response, "TT_CART", 
      JsonUtils.objectToJson(itemList), true);
    return TaotaoResult.ok();
  }
  
  private List<CartItem> getCatItemList(HttpServletRequest request)
  {
    String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
    if (cartJson == null) {
      return new ArrayList();
    }
    try
    {
      return JsonUtils.jsonToList(cartJson, CartItem.class);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return new ArrayList();
  }
  
  public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response)
  {
    List<CartItem> itemList = getCatItemList(request);
    return itemList;
  }
  
  public TaotaoResult updateCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response)
  {
    CartItem cartItem = null;
    
    List<CartItem> itemList = getCatItemList(request);
    for (CartItem cItem : itemList) {
      if (cItem.getId() == itemId)
      {
        cartItem = cItem;
        itemList.remove(cItem);
        cartItem.setNum(Integer.valueOf(num));
        break;
      }
    }
    itemList.add(cartItem);
    
    CookieUtils.setCookie(request, response, "TT_CART", 
      JsonUtils.objectToJson(itemList), true);
    
    return TaotaoResult.ok();
  }
  
  public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response)
  {
    List<CartItem> catItemList = getCatItemList(request);
    for (CartItem cItem : catItemList) {
      if (cItem.getId() == itemId)
      {
        catItemList.remove(cItem);
        break;
      }
    }
    CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(catItemList), true);
    
    return TaotaoResult.ok();
  }
}
