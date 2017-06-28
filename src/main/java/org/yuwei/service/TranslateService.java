package org.yuwei.service;

import org.yuwei.model.TranslateVo;
import org.yuwei.model.view.TranslateView;

public interface TranslateService {
  
  final String encodeType = "UTF-8";
  
  final String contenType = "application/json; charset=UTF-8";
  
  /**
   * This method is for get json from google translate server
   * @param target
   * @return
   */
  TranslateVo getTranslateResult(TranslateView translateView);
  
  /**
   * This method is for get simple String, and source from google translate json
   * And i just want get first one !! 
   * @param result
   * @return
   */
  String getformatResult(String result);
  
}