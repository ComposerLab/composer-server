package com.lab.composer.v2.controllers;

import com.lab.composer.v2.common.utils.DataResponse;
import com.lab.composer.v2.controllers.dto.ArticalDto;
import com.lab.composer.v2.services.ArticalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artical")
@RequiredArgsConstructor
public class ArticalController {
  private final ArticalService articalService;

  @GetMapping("/latest")
  public DataResponse<List<ArticalDto.ArticalSimple>> getLatest() {
    List<ArticalDto.ArticalSimple> articals = articalService.getLatest();

    return new DataResponse<>(articals);
  }
}
