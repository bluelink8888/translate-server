package org.yuwei.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.yuwei.model.BaseJson;

public class BaseController {

  public <T> ResponseEntity ok(Object object){
    BaseJson bj = new BaseJson();
    bj.setTime(this.nowTime());
    bj.setBody(object);
    
    return new ResponseEntity<>(bj, HttpStatus.OK);
  }
  
  public String nowTime(){
    LocalDateTime now = LocalDateTime.now();
    return now.toString();
  }
}
