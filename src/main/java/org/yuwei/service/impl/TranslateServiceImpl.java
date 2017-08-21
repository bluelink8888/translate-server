package org.yuwei.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuwei.model.TranslateVo;
import org.yuwei.model.enums.Language;
import org.yuwei.model.view.TranslateView;
import org.yuwei.service.TranslateService;
import org.yuwei.util.TokenImpl;

@Service
public class TranslateServiceImpl implements TranslateService{
  
  private final static Logger logger = Logger.getLogger(TranslateServiceImpl.class);

  @Autowired
  private TokenImpl token;
  
  @Override
  public TranslateVo getTranslateResult(TranslateView translateView) {
    
    String result = "";
    String googleUrl = "";
    
    /**
     * sl means user input language 
     * tl is user choose want to translate which language
     */
    try {
      googleUrl = "https://translate.google.com.tw/translate_a/single?client=t&"
          + "sl=" + translateView.getSl() + "&" 
          + "tl=" + translateView.getTl() + "&"  
          + "hl=" + Language.TRADITIONAL_CHINESE.getValue()
          + "&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t"
          + "&ie=" + encodeType + "&oe=" + encodeType
          + "&swap=1&source=btn&ssel=5&tsel=5&kc=0"
          + "&tk=" + token.getToken(translateView.getTarget()) + "&q=" + URLEncoder.encode(translateView.getTarget(), encodeType);
    } catch (Exception e) {
      logger.error("Error : " + e);
    }
    
    CloseableHttpClient client = HttpClientBuilder.create().build();
    HttpGet req = new HttpGet(googleUrl);

    try {
      HttpResponse resp = client.execute(req);
      resp.addHeader("Content-Type", contenType);
      if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        HttpEntity entity = resp.getEntity();
        BufferedReader bf = new BufferedReader(new InputStreamReader(entity.getContent()));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = bf.readLine()) != null) {
          sb.append(line);
        }
        result = this.getformatResult(sb.toString());
      }else{
      }
      client.close();
    } catch (Exception e) {
      logger.error("Error : " + e);
    }
    
    TranslateVo translateVo = new TranslateVo();
    translateVo.setTarget(translateView.getTarget());
    translateVo.setResult(result);
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

  @Override
  public String getTranslateFileResult(String path) {
    String result = "";
    try {
      CloseableHttpClient client = HttpClientBuilder.create().build();
      HttpPost post = new HttpPost(fileUrl);
      
      File file = new File(path);
      
      HttpEntity entity = MultipartEntityBuilder.create()
          .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
          .addTextBody("sl", "en")
          .addTextBody("tl", "zh-TW")
          .addTextBody("hl", "en")
          .addTextBody("js", "y")
          .addTextBody("ie", encodeType)
          .addTextBody("prev", "_t")
          .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName())
          .build();
      
      post.setEntity(entity);
      
      ResponseHandler<String> respHandler = new BasicResponseHandler();
      CloseableHttpResponse resp = client.execute(post);
      result = respHandler.handleResponse(resp).replace("<pre>", "").replace("</pre>", "");
      client.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}
