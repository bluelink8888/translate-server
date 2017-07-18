package org.yuwei.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yuwei.model.enums.Language;
import org.yuwei.model.view.TranslateView;
import org.yuwei.service.TranslateService;

@Controller
@RequestMapping("/")
public class TranslateController extends BaseController{
  
  private final static Logger logger = Logger.getLogger(TranslateController.class);
  
  @Autowired
  private TranslateService translateService;
  
  @RequestMapping(value="translate", method=RequestMethod.GET, produces="application/json")
  public ResponseEntity<String> getTranslate(@RequestParam(value="t") String t, 
      @RequestParam(value="sl", required = false) String sl, @RequestParam(value="tl", required = false) String tl){
    TranslateView view = new TranslateView();
    view.setTarget(t);  view.setSl(sl); view.setTl(tl);
    TranslateView result = this.vaildParam(view);
    return ok(translateService.getTranslateResult(result));
  }
  
  /**
   * According user input to translate, only for English to Chinese for now
   * @param translateView
   * @return
   */
  @RequestMapping(value="translate", method=RequestMethod.POST, produces="application/json")
  public ResponseEntity<String> postTranslate(@RequestBody TranslateView translateView){
    
    TranslateView result = this.vaildParam(translateView);
    if(result!=null){
      return ok(translateService.getTranslateResult(result));
    }else{
      return badRequest();
    }
  }
  
  private TranslateView vaildParam(TranslateView translateView){
    String defaultSl=Language.ENGLISH.getValue(), defaultTl = Language.TRADITIONAL_CHINESE.getValue();
    TranslateView result = null;
    if(translateView.getTarget()!=null){
      if(translateView.getSl()!=null){
        defaultSl = translateView.getSl();
      }
      if(translateView.getTl()!=null){
        defaultTl = translateView.getTl();
      }
      result = new TranslateView();
      result.setTarget(translateView.getTarget());
      result.setSl(defaultSl);
      result.setTl(defaultTl);
      logger.debug("target : " + result.getTarget() + ", sl : " + result.getSl() + ", tl : " + result.getTl());
    }
    return result;
  }
  
}
