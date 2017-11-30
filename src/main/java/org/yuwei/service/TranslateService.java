package org.yuwei.service;

import org.yuwei.model.TranslateVo;
import org.yuwei.model.bean.TranslateBean;

public interface TranslateService {
  
  /**
   * This method is for get json from google translate server
   * @param target
   * @return
   */
  TranslateVo getTranslateResult(TranslateBean translateBean);
  
}