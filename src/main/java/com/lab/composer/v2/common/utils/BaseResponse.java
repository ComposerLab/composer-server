package com.lab.composer.v2.common.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
  private boolean result;
  private String reason;

  public BaseResponse(){
    this.result = MessageUtils.success;
  }
  public BaseResponse(String reason){
    this.result = MessageUtils.fail;
    this.reason = reason;
  }

}