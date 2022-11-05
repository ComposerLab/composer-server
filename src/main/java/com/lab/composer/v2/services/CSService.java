package com.lab.composer.v2.services;

import com.lab.composer.v2.controllers.dto.CSDto.CSRequestDto;
import com.lab.composer.v2.controllers.dto.CSDto.CSResponseDto;
import com.lab.composer.v2.domain.CS;
import com.lab.composer.v2.repositories.CSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CSService {
  private final CSRepository csRepository;

  @Transactional
  public CSResponseDto update(CSRequestDto csRequestDto) {
    CS cs = csRequestDto.toEntity();

    csRepository.save(cs);

    CSResponseDto csResponseDto = cs.toDto();

    return csResponseDto;
  }
}
