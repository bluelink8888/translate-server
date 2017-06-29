package org.yuwei.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    TranslateView translateView = new TranslateView("測試",
        Language.TRADITIONAL_CHINESE.getValue(), Language.ENGLISH.getValue());
    assertEquals(
        translateService.getTranslateResult(translateView).getResult(), "test");
  }

  /**
   * check get google token in crawler way
   */
  @Test
  public void testToken() {
    assertNotNull(token.getToken("test"));
  }

}
