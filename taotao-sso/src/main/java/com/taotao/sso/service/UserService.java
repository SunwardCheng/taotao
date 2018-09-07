package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface UserService
{
  public abstract TaotaoResult checkData(String paramString, Integer paramInteger);
  
  public abstract TaotaoResult createUser(TbUser paramTbUser);
  
  public abstract TaotaoResult userLogin(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
  
  public abstract TaotaoResult getUserByToken(String paramString);
  
  public abstract TaotaoResult userLogout(String paramString);
}
