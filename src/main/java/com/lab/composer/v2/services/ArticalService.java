package com.lab.composer.v2.services;

import com.lab.composer.v2.controllers.dto.ArticalDto;
import com.lab.composer.v2.repositories.ArticalRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticalService {
  private final ArticalRepository articalRepository;

  public List<ArticalDto.ArticalSimple> getLatest() {
    return articalRepository.findAll().stream().map(ArticalDto.ArticalSimple::new).collect(Collectors.toList());
  }
}
