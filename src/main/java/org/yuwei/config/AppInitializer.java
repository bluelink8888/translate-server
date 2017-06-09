package org.yuwei.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

  // for init application setting
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { AppConfig.class };
  }

  // set application config
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
  
}
