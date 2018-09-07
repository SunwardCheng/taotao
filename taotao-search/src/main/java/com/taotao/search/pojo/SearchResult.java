package com.taotao.search.pojo;

import java.util.List;

public class SearchResult
{
  private List<Item> itemList;
  private long recordCount;
  private long pageCount;
  private long curPage;
  
  public List<Item> getItemList()
  {
    return this.itemList;
  }
  
  public void setItemList(List<Item> itemList)
  {
    this.itemList = itemList;
  }
  
  public long getRecordCount()
  {
    return this.recordCount;
  }
  
  public void setRecordCount(long recordCount)
  {
    this.recordCount = recordCount;
  }
  
  public long getPageCount()
  {
    return this.pageCount;
  }
  
  public void setPageCount(long pageCount)
  {
    this.pageCount = pageCount;
  }
  
  public long getCurPage()
  {
    return this.curPage;
  }
  
  public void setCurPage(long curPage)
  {
    this.curPage = curPage;
  }
}
