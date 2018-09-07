package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl
  implements ItemService
{
  @Autowired
  private TbItemMapper itemMapper;
  @Autowired
  private TbItemDescMapper itemDescMapper;
  @Autowired
  private TbItemParamItemMapper itemParamItemMapper;
  @Autowired
  private JedisClient jedisClient;
  @Value("${REDIS_ITEM_KEY}")
  private String REDIS_ITEM_KEY;
  @Value("${REDIS_ITEM_EXPIRE}")
  private int REDIS_ITEM_EXPIRE;
  
  public TaotaoResult getItemBaseInfo(long itemId)
  {
    try
    {
      String json = this.jedisClient.get(this.REDIS_ITEM_KEY + ":" + itemId + ":base");
      if (!StringUtils.isBlank(json))
      {
        TbItem item = (TbItem)JsonUtils.jsonToPojo(json, TbItem.class);
        return TaotaoResult.ok(item);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    TbItem item = this.itemMapper.selectByPrimaryKey(Long.valueOf(itemId));
    try
    {
      this.jedisClient.set(this.REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
      
      this.jedisClient.expire(this.REDIS_ITEM_KEY + ":" + itemId + ":base", this.REDIS_ITEM_EXPIRE);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return TaotaoResult.ok(item);
  }
  
  public TaotaoResult getItemDesc(long itemId)
  {
    try
    {
      String json = this.jedisClient.get(this.REDIS_ITEM_KEY + ":" + itemId + ":desc");
      if (!StringUtils.isBlank(json))
      {
        TbItemDesc itemDesc = (TbItemDesc)JsonUtils.jsonToPojo(json, TbItemDesc.class);
        return TaotaoResult.ok(itemDesc);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    TbItemDesc itemDesc = this.itemDescMapper.selectByPrimaryKey(Long.valueOf(itemId));
    try
    {
      this.jedisClient.set(this.REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
      
      this.jedisClient.expire(this.REDIS_ITEM_KEY + ":" + itemId + ":desc", this.REDIS_ITEM_EXPIRE);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return TaotaoResult.ok(itemDesc);
  }
  
  public TaotaoResult getItemParam(long itemId)
  {
    try
    {
      String json = this.jedisClient.get(this.REDIS_ITEM_KEY + ":" + itemId + ":param");
      if (!StringUtils.isBlank(json))
      {
        TbItemParamItem itemParamItem = (TbItemParamItem)JsonUtils.jsonToPojo(json, TbItemParamItem.class);
        return TaotaoResult.ok(itemParamItem);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    TbItemParamItemExample example = new TbItemParamItemExample();
    TbItemParamItemExample.Criteria criteria = example.createCriteria();
    criteria.andItemIdEqualTo(Long.valueOf(itemId));
    
    List<TbItemParamItem> list = this.itemParamItemMapper.selectByExampleWithBLOBs(example);
    if ((list != null) && (list.size() > 0))
    {
      TbItemParamItem itemParamItem = (TbItemParamItem)list.get(0);
      try
      {
        this.jedisClient.set(this.REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(itemParamItem));
        
        this.jedisClient.expire(this.REDIS_ITEM_KEY + ":" + itemId + ":param", this.REDIS_ITEM_EXPIRE);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      return TaotaoResult.ok(itemParamItem);
    }
    return TaotaoResult.build(Integer.valueOf(400), "无此商品规格信息");
  }
}
