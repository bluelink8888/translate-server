package org.yuwei.schedule.impl;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.yuwei.model.TranslateVo;
import org.yuwei.model.enums.Language;
import org.yuwei.model.view.TranslateView;
import org.yuwei.schedule.ServerSchedule;
import org.yuwei.service.TranslateService;


public class ServerScheduleImpl implements ServerSchedule{

  private static Logger logger = Logger.getLogger(ServerScheduleImpl.class);
  
  @Value("${test.target}")
  private String testTarget;

  @Value("${test.expect}")
  private String testExpect;
  
  @Autowired
  private TranslateService translateService;

  @Scheduled(cron="${test.cron}")
  @Override
  public void translateCheck() {
    LocalDateTime currentPoint = LocalDateTime.now();
    logger.debug("time : " + currentPoint);
    TranslateView view = new TranslateView();
    view.setTarget(testTarget);
    view.setSl(Language.ENGLISH.getValue());
    view.setTl(Language.TRADITIONAL_CHINESE.getValue());
    TranslateVo result = translateService.getTranslateResult(view);
    if(testExpect.equals(result.getResult())){
      logger.info("translate success");
    }else{
      logger.info("translate failure");
    }
  }
}
