package com.lab.composer.v2.common.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class AmazonS3ResourceStorage {
  @Value("${cloud.aws.s3.bucket}")
  private String bucket;
  private final AmazonS3Client amazonS3Client;

  public void store(String fullPath, MultipartFile multipartFile) {
    File file = new File(MultipartUtil.getLocalHomeDirectory(), fullPath);

    if (!file.exists()) { // 폴더가 없을 경우 폴더 만들기
      file.mkdirs();
    }

    try {
      multipartFile.transferTo(file);
      amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, file)
          .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (Exception e) {
      System.out.println("e : " + e);
      throw new RuntimeException();
    } finally {
      if (file.exists()) {
        file.delete();
      }
    }
  }
}
