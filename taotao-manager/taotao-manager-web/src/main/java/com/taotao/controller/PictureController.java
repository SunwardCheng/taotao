package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController
{
  @Autowired
  private PictureService pictureService;
  
  @RequestMapping({"/pic/upload"})
  @ResponseBody
  public String pictureUpload(MultipartFile uploadFile)
  {
    Map result = this.pictureService.uploadPicture(uploadFile);
    
    String json = JsonUtils.objectToJson(result);
    return json;
  }
}
