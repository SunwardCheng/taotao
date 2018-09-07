package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
  implements UserService
{
  @Value("${SSO_BASE_URL}")
  public String SSO_BASE_URL;
  @Value("${SSO_USER_TOKEN}")
  private String SSO_USER_TOKEN;
  @Value("${SSO_PAGE_LOGIN}")
  public String SSO_PAGE_LOGIN;
  @Value("${SSO_DOMAIN_BASE_URL}")
  public String SSO_DOMAIN_BASE_URL;
  
  public TbUser getUserByToken(String token)
  {
    try
    {
      String json = HttpClientUtil.doGet(this.SSO_BASE_URL + this.SSO_USER_TOKEN + token);
      
      TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
      if (result.getStatus().intValue() == 200) {
        return (TbUser)result.getData();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
