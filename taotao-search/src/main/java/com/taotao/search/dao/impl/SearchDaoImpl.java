package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDaoImpl
  implements SearchDao
{
  @Resource(name="cloudSolrClient")
  private SolrClient solrClient;
  
  public SearchResult search(SolrQuery query)
    throws Exception
  {
    SearchResult result = new SearchResult();
    
    QueryResponse queryResponse = this.solrClient.query(query);
    

    SolrDocumentList solrDocumentList = queryResponse.getResults();
    
    result.setRecordCount(solrDocumentList.getNumFound());
    

    List<Item> itemList = new ArrayList();
    
    Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
    for (SolrDocument solrDocument : solrDocumentList)
    {
      Item item = new Item();
      item.setId((String)solrDocument.get("id"));
      
      List<String> list = (List)((Map)highlighting.get(solrDocument.get("id"))).get("item_title");
      String title = "";
      if ((list != null) && (list.size() > 0)) {
        title = (String)list.get(0);
      } else {
        title = (String)solrDocument.get("item_title");
      }
      item.setTitle(title);
      item.setImage((String)solrDocument.get("item_image"));
      item.setPrice(((Long)solrDocument.get("item_price")).longValue());
      item.setSell_point((String)solrDocument.get("item_sell_point"));
      item.setCategory_name((String)solrDocument.get("item_category_name"));
      

      itemList.add(item);
    }
    result.setItemList(itemList);
    
    return result;
  }
}
