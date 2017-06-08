package org.yuwei.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

  private final static Logger logger = Logger.getLogger(IndexController.class);

  @RequestMapping(value="index", method=RequestMethod.GET, produces="text/html")
  public String index(){
    logger.info("index");
    return "index";
  }
  
}
