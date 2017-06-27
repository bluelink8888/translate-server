package org.yuwei.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yuwei.model.TranslateView;
import org.yuwei.service.TranslateService;

@Controller
@RequestMapping("/")
public class TranslateController extends BaseController{
  
  private final static Logger logger = Logger.getLogger(TranslateController.class);
  
  @Autowired
  private TranslateService translateService;
  
  /**
   * According user input to translate, only for English to Chinese for now
   * @param target
   * @return
   */
  @RequestMapping(value="translate", method=RequestMethod.POST, produces="application/json")
  public ResponseEntity<String> getUser(@RequestBody TranslateView translateView){
    logger.info(translateView.getTarget());
    return ok(translateService.getTranslateResult(translateView.getTarget(), 
        translateView.getSl(), translateView.getTl()));
  }
}
