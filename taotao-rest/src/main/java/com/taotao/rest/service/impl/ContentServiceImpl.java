package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl
  implements ContentService
{
  @Autowired
  private TbContentMapper contentMapper;
  @Autowired
  private JedisClient JedisClient;
  @Value("${INDEX_CONENT_REDIS_KEY}")
  private String INDEX_CONENT_REDIS_KEY;
  
  public List<TbContent> getContentList(long contentCid)
  {
    try
    {
      String result = this.JedisClient.hget(this.INDEX_CONENT_REDIS_KEY, contentCid + "");
      if (!StringUtils.isBlank(result)) {
        return JsonUtils.jsonToList(result, TbContent.class);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    TbContentExample example = new TbContentExample();
    TbContentExample.Criteria criteria = example.createCriteria();
    criteria.andCategoryIdEqualTo(Long.valueOf(contentCid));
    

    List<TbContent> list = this.contentMapper.selectByExample(example);
    try
    {
      String cacheString = JsonUtils.objectToJson(list);
      
      this.JedisClient.hset("INDEX_CONENT_REDIS_KEY", contentCid + "", cacheString);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return list;
  }
}
