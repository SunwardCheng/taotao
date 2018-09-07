package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

public abstract interface UserService
{
  public abstract TbUser getUserByToken(String paramString);
}
