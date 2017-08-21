package org.yuwei.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yuwei.config.AppConfig;
import org.yuwei.model.enums.Language;
import org.yuwei.model.view.TranslateView;
import org.yuwei.service.TranslateService;
import org.yuwei.util.TokenImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class BasicTest {

  private final static Logger logger = Logger.getLogger(BasicTest.class);

  @Autowired
  private TranslateService translateService;

  @Autowired
  private TokenImpl token;

  /**
   * check translate result
   */
  @Test
  public void testTranslate() {
    TranslateView translateView = new TranslateView();
    translateView.setTarget("測試");
    translateView.setSl(Language.TRADITIONAL_CHINESE.getValue());
    translateView.setTl(Language.ENGLISH.getValue());
    assertEquals(
        translateService.getTranslateResult(translateView).getResult(), "test");
  }
  
  /**
   * test translate file feature
   */
  @Test
  public void testTranslateFile(){
    try {
      URL url = this.getClass().getClassLoader().getResource("test.txt");
      if(new File(url.getFile()).exists()){
        assertEquals(translateService.getTranslateFileResult(url.getFile()), "測試");
      }else{
        logger.info("test not exist");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * check get google token in crawler way
   */
  @Test
  public void testToken() {
    assertNotNull(token.getToken("test"));
  }
}
