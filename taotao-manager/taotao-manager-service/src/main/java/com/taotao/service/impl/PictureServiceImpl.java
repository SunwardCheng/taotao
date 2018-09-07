package com.taotao.service.impl;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl
  implements PictureService
{
  @Value("${FTP_ADDRESS}")
  private String FTP_ADDRESS;
  @Value("${FTP_PORT}")
  private Integer FTP_PORT;
  @Value("${FTP_USERNAME}")
  private String FTP_USERNAME;
  @Value("${FTP_PASSWORD}")
  private String FTP_PASSWORD;
  @Value("${FTP_BASE_PATH}")
  private String FTP_BASE_PATH;
  @Value("${IMAGE_BASE_URL}")
  private String IMAGE_BASE_URL;
  
  public Map uploadPicture(MultipartFile uploadFile)
  {
    Map resultMap = new HashMap();
    try
    {
      //生成一个新的文件
      //取原文件名
      String oldName = uploadFile.getOriginalFilename();

      //生成新文件名
      //或者UUID.randomUUID
      String newName = IDUtils.genImageName();
      newName = newName + oldName.substring(oldName.lastIndexOf("."));
      
      String imagePath = new DateTime().toString("/yyyy/MM/dd");
      boolean result = FtpUtil.uploadFile(this.FTP_ADDRESS, this.FTP_PORT.intValue(), this.FTP_USERNAME, this.FTP_PASSWORD, this.FTP_BASE_PATH, imagePath, newName, uploadFile
        .getInputStream());
      if (!result)
      {
        resultMap.put("error", Integer.valueOf(1));
        resultMap.put("message", "文件上传失败");
        return resultMap;
      }
      resultMap.put("error", Integer.valueOf(0));
      resultMap.put("url", this.IMAGE_BASE_URL + imagePath + "/" + newName);
      return resultMap;
    }
    catch (IOException e)
    {
      resultMap.put("error", Integer.valueOf(1));
      resultMap.put("message", "文件上传出现异常");
    }
    return resultMap;
  }
}
