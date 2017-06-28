package org.yuwei.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yuwei.model.view.TranslateView;
import org.yuwei.service.TranslateService;

@Controller
@RequestMapping("/")
public class TranslateController extends BaseController{
  
  private final static Logger logger = Logger.getLogger(TranslateController.class);
  
  @Autowired
  private TranslateService translateService;
  
  /**
   * According user input to translate, only for English to Chinese for now
   * @param translateView
   * @return
   */
  @RequestMapping(value="translate", method=RequestMethod.POST, produces="application/json")
  public ResponseEntity<String> getUser(@RequestBody TranslateView translateView){
    
    TranslateView result = this.vaildParam(translateView);
    if(result!=null){
      return ok(translateService.getTranslateResult(result));
    }else{
      return badRequest();
    }
  }
  
  private TranslateView vaildParam(TranslateView translateView){
    String defaultSl="en", defaultTl = "zh-TW";
    TranslateView result = null;
    if(translateView.getTarget()!=null){
      result = new TranslateView();
      if(translateView.getSl()!=null){
        defaultSl = translateView.getSl();
      }
      if(translateView.getTl()!=null){
        defaultTl = translateView.getTl();
      }
      result.setSl(defaultSl);
      result.setTl(defaultTl);
      result.setTarget(translateView.getTarget());
      logger.debug("target : " + result.getTarget() + ", sl : " + result.getSl() + ", tl : " + result.getTl());
    }
    
    return result;
  }
  
}
