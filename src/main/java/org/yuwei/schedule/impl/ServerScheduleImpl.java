package org.yuwei.schedule.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.yuwei.schedule.ServerSchedule;

import com.github.bluelink8888.constant.Language;
import com.github.bluelink8888.translate.impl.TranslateImpl;


public class ServerScheduleImpl implements ServerSchedule{

  private static Logger logger = Logger.getLogger(ServerScheduleImpl.class);
  
  @Value("${test.target}")
  private String testTarget;

  @Value("${test.expect}")
  private String testExpect;
  
  @Scheduled(cron="${test.cron}")
  @Override
  public void translateCheck() {
    TranslateImpl.create();
    String result = TranslateImpl.create().translate(testTarget, Language.ENGLISH, Language.TRADITIONAL_CHINESE);
    if(testExpect.equals(result)){
      logger.info("translate success");
    }else{
      logger.warn("translate failure");
    }
  }
}
