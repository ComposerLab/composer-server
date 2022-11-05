package com.lab.composer.v2.controllers.dto;

import com.lab.composer.v2.common.utils.MultipartUtil;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FileDetailDto {
  private String id;
  private String name;
  private String format;
  private String path;
  private long bytes;

  @Builder.Default
  private LocalDateTime createdAt = LocalDateTime.now();

  public static FileDetailDto multipartOf(MultipartFile multipartFile, String path) {
    final String fileId = MultipartUtil.createFileId();
    final String format = MultipartUtil.getFormat(multipartFile.getContentType());
    return FileDetailDto.builder()
        .id(fileId)
        .name(multipartFile.getOriginalFilename())
        .format(format)
        .path(MultipartUtil.createPath(path, fileId, format))
        .bytes(multipartFile.getSize())
        .build();
  }
}
