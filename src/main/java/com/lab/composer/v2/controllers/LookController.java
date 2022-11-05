package com.lab.composer.v2.controllers;

import com.lab.composer.v2.common.utils.DataResponse;
import com.lab.composer.v2.controllers.dto.FileDetailDto;
import com.lab.composer.v2.controllers.dto.LookDto;
import com.lab.composer.v2.controllers.dto.LookDto.LookCalcDTO;
import com.lab.composer.v2.controllers.dto.LookDto.LookSimple;
import com.lab.composer.v2.controllers.dto.MemberDto;
import com.lab.composer.v2.controllers.dto.MemberResponseDto;
import com.lab.composer.v2.domain.LookAround;
import com.lab.composer.v2.domain.Member;
import com.lab.composer.v2.services.LookService;
import com.lab.composer.v2.services.MemberService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/look")
@RequiredArgsConstructor
public class LookController {
  private final LookService lookService;
  private final MemberService memberService;

  @GetMapping("/latest")
  public DataResponse<List<LookSimple>> getLatest() {
    List<LookDto.LookSimple> looks = lookService.getLatest();

    return new DataResponse<>(looks);
  }

  @GetMapping("/detail/{id}")
  public DataResponse<Optional<LookSimple>> getDetail(@PathVariable("id") String id) {
    Optional<LookSimple> lookDetail = lookService.getDetailInfo(id).map(LookDto.LookSimple::new);

    return new DataResponse<>(lookDetail);
  }

  @PostMapping(value = "/write")
  public LookAround upload(@RequestPart String title, @RequestPart String artist, @RequestPart String cost, @RequestPart MultipartFile image, @RequestPart MultipartFile music) {
    // S3에 이미지, 음악 업로드
    FileDetailDto uploadS3Image = lookService.uploadS3(image, "images");
    FileDetailDto uploadS3Music = lookService.uploadS3(music, "music");

    MemberResponseDto memberResponseDto = memberService.getMyInfo();
    MemberDto memberDto = memberService.getMember(memberResponseDto.getEmail());
    Member member = memberDto.toEntity();

    LookCalcDTO lookCalcDTO = new LookCalcDTO(title, artist, cost, uploadS3Image.getPath(), uploadS3Music.getPath(), member);

    return lookService.save(lookCalcDTO);
  }
}
