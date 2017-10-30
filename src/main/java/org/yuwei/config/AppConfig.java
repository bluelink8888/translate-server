package org.yuwei.config;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.yuwei.schedule.ServerSchedule;
import org.yuwei.schedule.impl.ServerScheduleImpl;

import com.github.bluelink8888.translate.Token;
import com.github.bluelink8888.translate.TokenImpl;

@EnableScheduling
@Configuration
@ComponentScan(basePackages = {"org.yuwei.service"})
@PropertySources(
    @PropertySource("classpath:config.properties")
    )
public class AppConfig {
  
  private static Logger logger = Logger.getLogger(AppConfig.class);
  
  @Bean
  public Token tokenImpl(){
    Token token = null;
    try {
      token = new TokenImpl();
    } catch (Exception e) {
      logger.error(e);
    }
    return token;
  }
  
  // schedule job 
  @Bean
  public ServerSchedule translateSchedule(){
    return new ServerScheduleImpl();
  }
  
}