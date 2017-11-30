package org.yuwei.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yuwei.model.bean.TranslateBean;
import org.yuwei.model.view.TranslateView;
import org.yuwei.service.TranslateService;

import com.github.bluelink8888.constant.Language;

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
    TranslateBean result = this.vaildParam(view);
    return ok(translateService.getTranslateResult(result));
  }
  
  /**
   * According user input to translate, only for English to Chinese for now
   * @param translateView
   * @return
   */
  @RequestMapping(value="translate", method=RequestMethod.POST, produces="application/json")
  public ResponseEntity<String> postTranslate(@RequestBody TranslateView translateView){
    
    TranslateBean result = this.vaildParam(translateView);
    if(result!=null){
      return ok(translateService.getTranslateResult(result));
    }else{
      return badRequest();
    }
  }
  
  private TranslateBean vaildParam(TranslateView translateView){
    Language defaultSl=Language.ENGLISH, defaultTl = Language.TRADITIONAL_CHINESE;
    TranslateBean result = null;
    if(translateView.getTarget()!=null){
      // check want translate String is not null
      if(translateView.getSl()!=null){
        for(Language lang : Language.values()){
          if(lang.getValue().equals(translateView.getSl())){
            // vaild param is expected
            defaultSl = lang;
          }
        }
      }
      if(translateView.getTl()!=null){
        for(Language lang : Language.values()){
          if(lang.getValue().equals(translateView.getTl())){
            // vaild param is expected
            defaultTl = lang;
          }
        }
      }
      result = new TranslateBean();
      result.setTarget(translateView.getTarget());
      result.setSl(defaultSl);
      result.setTl(defaultTl);
      logger.debug("target : " + result.getTarget() + ", sl : " + result.getSl() + ", tl : " + result.getTl());
    }
    return result;
  }
  
}
