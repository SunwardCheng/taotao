package com.taotao.search.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import java.util.List;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl
  implements ItemService
{
  @Autowired
  private ItemMapper itemMapper;
  @Autowired
  SolrClient solrClient;
  
  public TaotaoResult importAllItems()
  {
    List<Item> list = this.itemMapper.getItemList();
    try
    {
      for (Item item : list)
      {
        SolrInputDocument document = new SolrInputDocument(new String[0]);
        document.setField("id", item.getId());
        document.setField("item_title", item.getTitle());
        document.setField("item_sell_point", item.getSell_point());
        document.setField("item_price", Long.valueOf(item.getPrice()));
        document.setField("item_image", item.getImage());
        document.setField("item_category_name", item.getCategory_name());
        document.setField("item_desc", item.getItem_des());
        
        this.solrClient.add(document);
      }
      this.solrClient.commit();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
    return TaotaoResult.ok();
  }
}
