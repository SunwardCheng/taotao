package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentCategoryServiceImpl
  implements ContentCategoryService
{
  @Autowired
  private TbContentCategoryMapper contentCategoryMapper;
  
  /**
   * 内容分类列表
   */
  public List<EUTreeNode> getCategoryList(long parentId)
  {
    TbContentCategoryExample example = new TbContentCategoryExample();
    TbContentCategoryExample.Criteria criteria = example.createCriteria();
    criteria.andParentIdEqualTo(Long.valueOf(parentId));
    
    List<TbContentCategory> list = this.contentCategoryMapper.selectByExample(example);
    List<EUTreeNode> resultList = new ArrayList();
    for (TbContentCategory tbContentCategory : list)
    {
      EUTreeNode node = new EUTreeNode();
      node.setId(tbContentCategory.getId().longValue());
      node.setText(tbContentCategory.getName());
      node.setState(tbContentCategory.getIsParent().booleanValue() ? "closed" : "open");
      resultList.add(node);
    }
    return resultList;
  }
  
  public TaotaoResult insertContentCategory(long parentId, String name)
  {
    TbContentCategory contentCategory = new TbContentCategory();
    contentCategory.setParentId(Long.valueOf(parentId));
    contentCategory.setName(name);
    contentCategory.setIsParent(Boolean.valueOf(false));
    
    contentCategory.setStatus(Integer.valueOf(1));
    contentCategory.setSortOrder(Integer.valueOf(1));
    contentCategory.setCreated(new Date());
    contentCategory.setUpdated(new Date());
    

    this.contentCategoryMapper.insert(contentCategory);
    
    TbContentCategory parentCat = this.contentCategoryMapper.selectByPrimaryKey(Long.valueOf(parentId));
    if (!parentCat.getIsParent().booleanValue())
    {
      parentCat.setIsParent(Boolean.valueOf(true));
      
      this.contentCategoryMapper.updateByPrimaryKey(parentCat);
    }
    return TaotaoResult.ok(contentCategory);
  }
  
  public TaotaoResult deleteContentCategory(long id)
    throws Exception
  {
    TbContentCategoryExample example = new TbContentCategoryExample();
    TbContentCategoryExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(Long.valueOf(id));
    

    TbContentCategory contentCategory = new TbContentCategory();
    
    List<TbContentCategory> list = this.contentCategoryMapper.selectByExample(example);
    if ((list != null) && (list.size() > 0)) {
      contentCategory = (TbContentCategory)list.get(0);
    }
    long parentId = contentCategory.getParentId().longValue();
    

    TbContentCategory parentContentCategory = new TbContentCategory();
    example = new TbContentCategoryExample();
    criteria = example.createCriteria();
    criteria.andIdEqualTo(Long.valueOf(parentId));
    List<TbContentCategory> parentList = this.contentCategoryMapper.selectByExample(example);
    if ((parentList != null) && (parentList.size() > 0)) {
      parentContentCategory = (TbContentCategory)parentList.get(0);
    }
    if (contentCategory.getIsParent().booleanValue())
    {
      example = new TbContentCategoryExample();
      criteria = example.createCriteria();
      criteria.andParentIdEqualTo(Long.valueOf(id));
      List<TbContentCategory> sonList = this.contentCategoryMapper.selectByExample(example);
      for (TbContentCategory tbContentCategory : sonList) {
        if (deleteContenCategoryById(tbContentCategory.getId().longValue()) == null) {
          throw new Exception();
        }
      }
    }
    if (deleteContenCategoryById(id) == null) {
      throw new Exception();
    }
    if (parentContentCategory != null)
    {
      example = new TbContentCategoryExample();
      criteria = example.createCriteria();
      criteria.andParentIdEqualTo(Long.valueOf(parentId));
      List<TbContentCategory> sonList = this.contentCategoryMapper.selectByExample(example);
      if ((sonList == null) || (sonList.size() < 1))
      {
        parentContentCategory.setIsParent(Boolean.valueOf(false));
        this.contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
      }
      return TaotaoResult.ok();
    }
    return null;
  }
  
  private TaotaoResult deleteContenCategoryById(long id)
  {
    TbContentCategoryExample example = new TbContentCategoryExample();
    TbContentCategoryExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(Long.valueOf(id));
    
    int result = this.contentCategoryMapper.deleteByExample(example);
    if (result == 1) {
      return TaotaoResult.ok();
    }
    return null;
  }
  
  public TaotaoResult updateContentCategory(long id, String name)
  {
    TbContentCategory contentCategory = new TbContentCategory();
    TbContentCategoryExample example = new TbContentCategoryExample();
    TbContentCategoryExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(Long.valueOf(id));
    List<TbContentCategory> list = this.contentCategoryMapper.selectByExample(example);
    if ((list != null) && (list.size() > 0))
    {
      contentCategory = (TbContentCategory)list.get(0);
      contentCategory.setName(name);
      this.contentCategoryMapper.updateByPrimaryKey(contentCategory);
      return TaotaoResult.ok();
    }
    return null;
  }
}
