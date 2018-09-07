package com.taotao.common.pojo;

import java.util.List;

public class EUDataGridResult
{
  private long total;
  private List<?> rows;
  
  public long getTotal()
  {
    return this.total;
  }
  
  public void setTotal(long total)
  {
    this.total = total;
  }
  
  public List<?> getRows()
  {
    return this.rows;
  }
  
  public void setRows(List<?> rows)
  {
    this.rows = rows;
  }
}
