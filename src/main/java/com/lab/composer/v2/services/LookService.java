package com.lab.composer.v2.services;

import com.lab.composer.v2.common.utils.AmazonS3ResourceStorage;
import com.lab.composer.v2.controllers.dto.FileDetailDto;
import com.lab.composer.v2.controllers.dto.LookDto;
import com.lab.composer.v2.controllers.dto.LookDto.LookCalcDTO;
import com.lab.composer.v2.controllers.dto.LookDto.LookSimple;
import com.lab.composer.v2.domain.LookAround;
import com.lab.composer.v2.repositories.LookRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class LookService {
  private final LookRepository lookRepository;
  private final AmazonS3ResourceStorage amazonS3ResourceStorage;

  public List<LookSimple> getLatest() {
    return lookRepository.findAll().stream().map(LookDto.LookSimple::new).collect(Collectors.toList());
  }

  @Transactional
  public Optional<LookAround> getDetailInfo(String id) {
    return lookRepository.findById(Long.valueOf(id));
  }

  public FileDetailDto uploadS3(MultipartFile file, String path) {
    FileDetailDto FileDetail = FileDetailDto.multipartOf(file, path);
    amazonS3ResourceStorage.store(FileDetail.getPath(), file);

    return FileDetail;
  }

  public LookAround save(LookCalcDTO lookCalcDTO) {
    LookAround lookAround = lookCalcDTO.toEntity();
    return lookRepository.save(lookAround);
  }
}
