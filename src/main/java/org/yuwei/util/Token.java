package org.yuwei.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

/**
 * This for have get token needed
 * @author YuWeiHung
 *
 */
public abstract class Token {

  private final static Logger logger = Logger.getLogger(Token.class);

  // google translate page url
  private String googleUrl = "https://translate.google.com.tw/";

  private List<Long> tkArray;

  public Token() throws ClientProtocolException, IOException {
    // Design got this param when server start up 
    tkArray = this.getGoogleArray();
  }

  /**
   * This method is get generate token need init param
   * 
   * @return
   * @throws ClientProtocolException
   * @throws IOException
   */
  private List<Long> getGoogleArray() throws ClientProtocolException,
      IOException {

    CloseableHttpClient client = HttpClientBuilder.create().build();
    HttpGet req = new HttpGet(googleUrl);

    String tkk = null;

    CloseableHttpResponse resp = client.execute(req);
    if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
      HttpEntity entity = resp.getEntity();
      BufferedReader bf = new BufferedReader(new InputStreamReader(
          entity.getContent()));
      StringBuffer sb = new StringBuffer();
      String line = "";
      while ((line = bf.readLine()) != null) {
        sb.append(line);
      }

      String target = sb.toString();
      tkk = target.substring(target.indexOf("TKK="),
          target.indexOf("WEB_TRANSLATION_PATH") - 1);
    } else {
      logger.warn("Fail to crawler google param ");
    }
    client.close();
    
    String[] tkks = tkk.split(";");

    List<Long> result = new ArrayList<Long>();

    for (int i = (tkks.length - 1); i >= 0; i--) {
      long temp = 0;
      if (i == 2) {
        temp = Long.parseLong(tkks[i].substring(tkks[i].indexOf(" ") + 1,
            tkks[i].indexOf("+")));
        result.add(temp);
      } else {
        temp = Long.parseLong(tkks[i].substring(tkks[i].indexOf("x3d") + 3));
        result.add(temp);
      }
    }
    logger.debug("google param : " + result);
    return result;
  }

  protected List<Long> getTkArray() {
    return tkArray;
  }

  public abstract String getToken(String input);
}
