package org.yuwei.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuwei.model.TranslateVo;
import org.yuwei.service.TranslateService;
import org.yuwei.util.TokenImpl;

@Service
public class TranslateServiceImpl implements TranslateService{
  
  private final static Logger logger = Logger.getLogger(TranslateServiceImpl.class);

  @Autowired
  private TokenImpl token;
  
  @Override
  public TranslateVo getTranslateResult(String target) {
    
    String result = "";
    String googleUrl = "";
    
    try {
      googleUrl = "https://translate.google.com.tw/translate_a/single?"
          + "client=t&sl=en&tl=" + translateLanguage + "&hl=" + translateLanguage
          + "&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t"
          + "&ie=" + encodeType + "&oe=" + encodeType
          + "&swap=1&source=btn&ssel=5&tsel=5&kc=0"
          + "&tk=" + token.getToken(target) + "&q=" + URLEncoder.encode(target, encodeType);
    } catch (UnsupportedEncodingException e1) {
      logger.error(e1);
    }
    
    HttpClient client = HttpClientBuilder.create().build();
    HttpGet req = new HttpGet(googleUrl);

    try {
      HttpResponse resp = client.execute(req);
      resp.addHeader("Content-Type", "application/json; charset=UTF-8");
      if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        HttpEntity entity = resp.getEntity();
        BufferedReader bf = new BufferedReader(new InputStreamReader(
            entity.getContent()));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = bf.readLine()) != null) {
          sb.append(line);
        }
        result = sb.toString();
      }else{
        logger.warn("failure");
      }
    } catch (Exception e) {
      logger.error(e);
    }
    
    TranslateVo translateVo = new TranslateVo();
    translateVo.setTarget(target);
    translateVo.setResult(this.getformatResult(result));
    
    return translateVo;
  }

  @Override
  public String getformatResult(String result) {
    
    if(result !=null && result.length()!= 0){
      // i know this is a stupid way to get first one result, hope one day can get better idea
      result = result.substring(4, (result.indexOf(",")-1));
    }
    
    return result;
  }

}
