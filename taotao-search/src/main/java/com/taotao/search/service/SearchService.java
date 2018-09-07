package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

public abstract interface SearchService
{
  public abstract SearchResult search(String paramString, int paramInt1, int paramInt2)
    throws Exception;
}
