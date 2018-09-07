package com.taotao.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public abstract interface PictureService
{
  public abstract Map uploadPicture(MultipartFile paramMultipartFile);
}
