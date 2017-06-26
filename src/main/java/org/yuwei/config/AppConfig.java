package org.yuwei.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.yuwei.service", "org.yuwei.util"})
public class AppConfig {
  
}