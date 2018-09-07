package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public abstract interface SearchDao
{
  public abstract SearchResult search(SolrQuery paramSolrQuery)
    throws Exception;
}
