package com.taotao.portal.interceptor;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor
  implements HandlerInterceptor
{
  @Autowired
  private UserServiceImpl userService;
  
  /**
   * 在controller具体方法之前处理，返回值决定Handler（controller）是否执行，true：执行，false：不执行，被拦截
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception
  {
    String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
    
    TbUser user = this.userService.getUserByToken(token);
    if (null == user)
    {
      response.sendRedirect(this.userService.SSO_DOMAIN_BASE_URL + this.userService.SSO_PAGE_LOGIN + "?redirect=" + request
        .getRequestURL());
      
      return false;
    }
    request.setAttribute("user", user);
    
    return true;
  }
  
  /**
   * 在返回modelAndView之前处理，完成具体方法之后调用
   */
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    throws Exception
  {}
  
  /**
   *  在返回modelAndView之后处理，完成对页面的render以后调用
   */
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    throws Exception
  {}
}
