package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

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
  
  public TbItem getItemById(long itemId)
  {
    TbItemExample example = new TbItemExample();
    
    TbItemExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(Long.valueOf(itemId));
    
    List<TbItem> list = this.itemMapper.selectByExample(example);
    if ((list != null) && (list.size() > 0))
    {
      TbItem item = (TbItem)list.get(0);
      return item;
    }
    return null;
  }
  
  /**
   * 获取商品的类别异步Tree列表
   */
  public EUDataGridResult getItemList(int page, int rows)
  {
    TbItemExample example = new TbItemExample();
    
    PageHelper.startPage(page, rows);
    List<TbItem> list = this.itemMapper.selectByExample(example);
    
    EUDataGridResult result = new EUDataGridResult();
    result.setRows(list);
    
    PageInfo<TbItem> pageInfo = new PageInfo(list);
    result.setTotal(pageInfo.getTotal());
    return result;
  }
  
  /**
   * 创建商品
   */
  public TaotaoResult createItem(TbItem item, String desc, String itemParams)
    throws Exception
  {
    Long itemId = Long.valueOf(IDUtils.genItemId());
    item.setId(itemId);
    
    item.setStatus(Byte.valueOf((byte)1));
    
    item.setCreated(new Date());
    
    item.setUpdated(new Date());
    
    int result = this.itemMapper.insert(item);
    

    /**
     * 抛异常让Spring事务回滚
     */
    TaotaoResult res = inserItemDesc(itemId, desc);
    if (res.getStatus().intValue() != 200) {
      throw new Exception();
    }
    res = insertItemParamItem(itemId, itemParams);
    if (res.getStatus().intValue() != 200) {
      throw new Exception();
    }
    return TaotaoResult.ok();
  }
  
  /**
   * 
  * @Title: inserItemDesc 
  * @Description: TODO 添加商品描述
  * @param @param itemId
  * @param @param desc
  * @param @return    
  * @return TaotaoResult    返回类型 
  * @throws
   */
  private TaotaoResult inserItemDesc(Long itemId, String desc)
  {
    TbItemDesc itemDesc = new TbItemDesc();
    itemDesc.setItemId(itemId);
    itemDesc.setItemDesc(desc);
    itemDesc.setCreated(new Date());
    itemDesc.setUpdated(new Date());
    this.itemDescMapper.insert(itemDesc);
    return TaotaoResult.ok();
  }
  
  /**
   * 
  * @Title: insertItemParamItem 
  * @Description: TODO 添加规格参数
  * @param @param itemId
  * @param @param itemParams
  * @param @return    
  * @return TaotaoResult    返回类型 
  * @throws
   */
  private TaotaoResult insertItemParamItem(Long itemId, String itemParams)
  {
    TbItemParamItem itemParamItem = new TbItemParamItem();
    itemParamItem.setItemId(itemId);
    itemParamItem.setParamData(itemParams);
    itemParamItem.setCreated(new Date());
    itemParamItem.setUpdated(new Date());
    System.out.println(JsonUtils.objectToJson(itemParams) + "这个有没有呢");
    
    this.itemParamItemMapper.insert(itemParamItem);
    return TaotaoResult.ok();
  }
}
