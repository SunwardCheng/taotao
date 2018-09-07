package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl
  implements UserService
{
  @Autowired
  private TbUserMapper userMapper;
  @Autowired
  private JedisClient jedisClient;
  @Value("${REDIS_USER_SESSION_KEY}")
  private String REDIS_USER_SESSION_KEY;
  @Value("${SSO_SESSION_EXPIRE}")
  private int SSO_SESSION_EXPIRE;
  
  /**
   * 查询用户是否存在
   */
  public TaotaoResult checkData(String content, Integer type)
  {
    TbUserExample example = new TbUserExample();
    TbUserExample.Criteria criteria = example.createCriteria();
    if (type.intValue() == 1) {
      criteria.andUsernameEqualTo(content);
    } else if (type.intValue() == 2) {
      criteria.andPhoneEqualTo(content);
    } else {
      criteria.andEmailEqualTo(content);
    }
    List<TbUser> list = this.userMapper.selectByExample(example);
    if ((list == null) || (list.size() == 0)) {
      return TaotaoResult.ok(Boolean.valueOf(true));
    }
    return TaotaoResult.ok(Boolean.valueOf(false));
  }
  
  /**
   *注册用户
   */
  public TaotaoResult createUser(TbUser user)
  {
    user.setCreated(new Date());
    user.setUpdated(new Date());
    

    /**
     * spring框架提供的MD5加密算法加密密码
     */
    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
    int result = this.userMapper.insert(user);
    if (result == 1) {
      return TaotaoResult.ok();
    }
    return TaotaoResult.ok(Boolean.valueOf(false));
  }
  
  /**
   * 用户登录
   */
  public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response)
  {
    TbUserExample example = new TbUserExample();
    TbUserExample.Criteria criteria = example.createCriteria();
    criteria.andUsernameEqualTo(username);
    List<TbUser> list = this.userMapper.selectByExample(example);
    if ((null == list) || (list.size() == 0)) {
      return TaotaoResult.build(Integer.valueOf(400), "用户名或密码错误");
    }
    TbUser user = (TbUser)list.get(0);
    if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
      return TaotaoResult.build(Integer.valueOf(400), "用户名或密码错误");
    }
    String token = UUID.randomUUID().toString();
    
    user.setPassword(null);
    
    this.jedisClient.set(this.REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
    this.jedisClient.expire(this.REDIS_USER_SESSION_KEY + ":" + token, this.SSO_SESSION_EXPIRE);
    

    CookieUtils.setCookie(request, response, "TT_TOKEN", token);
    

    return TaotaoResult.ok(token);
  }
  
  /**
   * 获取用户信息，查看是否登陆
   */
  public TaotaoResult getUserByToken(String token)
  {
	  //根据token从redis查询用户信息
    String json = this.jedisClient.get(this.REDIS_USER_SESSION_KEY + ":" + token);
    if (StringUtils.isBlank(json)) {
      return TaotaoResult.build(Integer.valueOf(400), "用户已过期，请重新登录");
    }
    //判断是否为空
    this.jedisClient.expire(this.REDIS_USER_SESSION_KEY + ":" + token, this.SSO_SESSION_EXPIRE);
    //更新过期时间
    return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
  }
  
  public TaotaoResult userLogout(String token)
  {
    long result = this.jedisClient.del(this.REDIS_USER_SESSION_KEY + ":" + token);
    if (result == 1L) {
      return TaotaoResult.ok();
    }
    return TaotaoResult.build(Integer.valueOf(400), "用户登录已过期");
  }
}
