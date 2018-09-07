package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

public abstract interface SearchService
{
  public abstract SearchResult search(String paramString, int paramInt);
}
