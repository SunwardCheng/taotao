package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemParamService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamServiceImpl
  implements ItemParamService
{
  @Autowired
  private TbItemParamMapper itemParamMapper;
  @Autowired
  private ItemCatService itemCatService;
  
  public TaotaoResult getItemParamById(long cid)
  {
    TbItemParamExample example = new TbItemParamExample();
    TbItemParamExample.Criteria criteria = example.createCriteria();
    criteria.andItemCatIdEqualTo(Long.valueOf(cid));
    List<TbItemParam> list = this.itemParamMapper.selectByExampleWithBLOBs(example);
    if ((list != null) && (list.size() > 0)) {
      return TaotaoResult.ok(list.get(0));
    }
    return TaotaoResult.ok();
  }
  
  public TaotaoResult insertItemParam(TbItemParam itemParam)
  {
    itemParam.setCreated(new Date());
    itemParam.setUpdated(new Date());
    
    this.itemParamMapper.insert(itemParam);
    return TaotaoResult.ok();
  }
  
  public EUDataGridResult getItemParamList(int page, int rows)
    throws Exception
  {
    TbItemParamExample example = new TbItemParamExample();
    
    PageHelper.startPage(page, rows);
    List<TbItemParam> list = this.itemParamMapper.selectByExampleWithBLOBs(example);
    
    List<Map<Object, Object>> list2 = new ArrayList();
    Map<Object, Object> map = null;
    for (TbItemParam itemParam : list)
    {
      map = new HashMap();
      
      long itemCatId = itemParam.getItemCatId().longValue();
      
      String string = this.itemCatService.getItemCatName(Long.valueOf(itemCatId));
      if (string == null) {
        throw new Exception();
      }
      map.put("id", itemParam.getId());
      map.put("itemCatId", itemParam.getItemCatId());
      map.put("itemCatName", string);
      map.put("paramData", itemParam.getParamData());
      map.put("created", itemParam.getCreated());
      map.put("updated", itemParam.getUpdated());
      
      list2.add(map);
    }
    EUDataGridResult result = new EUDataGridResult();
    result.setRows(list2);
    
    PageInfo pageInfo = new PageInfo(list);
    result.setTotal(pageInfo.getTotal());
    
    return result;
  }
}
