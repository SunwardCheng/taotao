package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class UserController
{
  @Autowired
  private UserService userService;
  
  @RequestMapping({"/check/{param}/{type}"})
  @ResponseBody
  public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback)
  {
    TaotaoResult result = null;
    if (StringUtils.isBlank(param)) {
      result = TaotaoResult.build(Integer.valueOf(400), "校验内容不能为空");
    }
    if (type == null) {
      result = TaotaoResult.build(Integer.valueOf(400), "校验内容类型不能为空");
    }
    if ((type.intValue() != 1) && (type.intValue() != 2) && (type.intValue() != 3)) {
      result = TaotaoResult.build(Integer.valueOf(400), "校验内容类型错误");
    }
    if (null != result)
    {
      if (null != callback)
      {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
      }
      return result;
    }
    try
    {
      result = this.userService.checkData(param, type);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      result = TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
    if (null != callback)
    {
      MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
      mappingJacksonValue.setJsonpFunction(callback);
      return mappingJacksonValue;
    }
    return result;
  }
  
  @RequestMapping(value={"/register"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public TaotaoResult createUser(TbUser user)
  {
    try
    {
      return this.userService.createUser(user);
    }
    catch (Exception e)
    {
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
  }
  
  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      return this.userService.userLogin(username, password, request, response);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
  }
  
  @RequestMapping({"/token/{token}"})
  @ResponseBody
  public Object getUserByToken(@PathVariable String token, String callback)
  {
    TaotaoResult result = null;
    try
    {
      result = this.userService.getUserByToken(token);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
    if (StringUtils.isBlank(callback)) {
      return result;
    }
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
    mappingJacksonValue.setJsonpFunction(callback);
    return mappingJacksonValue;
  }
  
  @RequestMapping({"/logout/{token}"})
  @ResponseBody
  public Object userLogout(@PathVariable String token, String callback)
  {
    TaotaoResult result = null;
    try
    {
      result = this.userService.userLogout(token);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return TaotaoResult.build(Integer.valueOf(500), ExceptionUtil.getStackTrace(e));
    }
    if (StringUtils.isBlank(callback)) {
      return result;
    }
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
    mappingJacksonValue.setJsonpFunction(callback);
    return mappingJacksonValue;
  }
}
