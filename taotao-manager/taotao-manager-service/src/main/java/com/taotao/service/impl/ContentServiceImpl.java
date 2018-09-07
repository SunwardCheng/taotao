package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl
  implements ContentService
{
  @Autowired
  private TbContentMapper contentMapper;
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  @Value("${REST_CONTENT_SYNC_URL}")
  private String REST_CONTENT_SYNC_URL;
  
  public EUDataGridResult getContentList(int page, int rows, Long categoryId)
  {
    EUDataGridResult dataGridResult = new EUDataGridResult();
    PageHelper.startPage(page, rows);
    
    List<TbContent> list = this.contentMapper.selectByCategoryId(categoryId);
    if ((list != null) && (list.size() > 0))
    {
      PageInfo<TbContent> pageInfo = new PageInfo(list);
      dataGridResult.setRows(list);
      dataGridResult.setTotal(pageInfo.getTotal());
      return dataGridResult;
    }
    dataGridResult.setRows(null);
    dataGridResult.setTotal(0L);
    
    return dataGridResult;
  }
  
  public TaotaoResult insertContent(TbContent content)
  {
    content.setUpdated(new Date());
    content.setCreated(new Date());
    this.contentMapper.insert(content);
    try
    {
      HttpClientUtil.doGet(this.REST_BASE_URL + this.REST_CONTENT_SYNC_URL + content.getCategoryId());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return TaotaoResult.ok();
  }
  
  public TaotaoResult updateContent(TbContent content)
  {
    this.contentMapper.updateByPrimaryKeyWithBLOBs(content);
    return TaotaoResult.ok();
  }
  
  public TaotaoResult deleteContent(Long id)
  {
    TbContentExample example = new TbContentExample();
    TbContentExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(id);
    this.contentMapper.deleteByExample(example);
    return TaotaoResult.ok();
  }
}
