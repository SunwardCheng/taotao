package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;
import java.io.PrintStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController
{
  @Autowired
  private ContentService contentService;
  
  @RequestMapping({"/index"})
  public String showIndex(Model model)
  {
    String adjson = this.contentService.getContentList();
    model.addAttribute("ad1", adjson);
    return "index";
  }
  
  @RequestMapping(value={"/httpclient/post"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"text/plain;utf-8"})
  @ResponseBody
  public TaotaoResult testPost(String username, String password)
  {
    String string = "username:" + username + " \tpassword:" + password;
    System.out.println(string);
    return TaotaoResult.ok();
  }
}
