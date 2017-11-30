package org.yuwei.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.yuwei.model.TranslateVo;
import org.yuwei.model.bean.TranslateBean;
import org.yuwei.service.TranslateService;

import com.github.bluelink8888.translate.impl.TranslateImpl;

@Service
public class TranslateServiceImpl implements TranslateService{
  
  private final static Logger logger = Logger.getLogger(TranslateServiceImpl.class);

  @Override
  public TranslateVo getTranslateResult(TranslateBean translateBean) {
    long sTime = System.currentTimeMillis();
    String result = TranslateImpl.create().
        translate(translateBean.getTarget(), translateBean.getSl(), translateBean.getTl());
    logger.debug("time spend : " + (System.currentTimeMillis() - sTime));
    TranslateVo translateVo = new TranslateVo();
    translateVo.setTarget(translateBean.getTarget());
    translateVo.setResult(result);
    return translateVo;
  }
}
