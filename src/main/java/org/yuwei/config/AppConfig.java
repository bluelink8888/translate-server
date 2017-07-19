package org.yuwei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.yuwei.schedule.ServerSchedule;
import org.yuwei.schedule.impl.ServerScheduleImpl;

@EnableScheduling
@Configuration
@ComponentScan(basePackages = {"org.yuwei.service", "org.yuwei.util"})
@PropertySources(
    @PropertySource("classpath:config.properties")
    )
public class AppConfig {
  
  // schedule job 
  @Bean
  public ServerSchedule translateSchedule(){
    return new ServerScheduleImpl();
  }
  
}