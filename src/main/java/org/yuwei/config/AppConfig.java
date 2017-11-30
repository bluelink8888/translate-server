package org.yuwei.config;

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
import com.github.bluelink8888.translate.impl.TokenImpl;


@EnableScheduling
@Configuration
@ComponentScan(basePackages = {"org.yuwei.service"})
@PropertySources(
    @PropertySource("classpath:config.properties")
    )
public class AppConfig {
  
  private static Logger logger = Logger.getLogger(AppConfig.class);
  
  // schedule job 
  @Bean
  public ServerSchedule translateSchedule(){
    return new ServerScheduleImpl();
  }
  
}