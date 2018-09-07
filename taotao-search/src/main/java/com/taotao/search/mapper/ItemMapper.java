package com.taotao.search.mapper;

import com.taotao.search.pojo.Item;
import java.util.List;

public abstract interface ItemMapper
{
  public abstract List<Item> getItemList();
}
