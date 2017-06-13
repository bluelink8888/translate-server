package org.yuwei.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yuwei.service.TranslateService;

@Controller
@RequestMapping("/translate")
public class TranslateController extends BaseController{
  
  private final static Logger logger = Logger.getLogger(TranslateController.class);
  
  @Autowired
  private TranslateService translateService;
  
  /**
   * According user input to translate, only for English to Chinese for now
   * @param target
   * @return
   */
  @RequestMapping(value="/{target}", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<String> getUser(@PathVariable String target){
    
    return ok(translateService.getTranslateResult(target));
  }
}
