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
import org.yuwei.model.bean.TranslateBean;
import org.yuwei.service.TranslateService;

import com.github.bluelink8888.constant.Language;
import com.github.bluelink8888.translate.Token;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class BasicTest {

  private final static Logger logger = Logger.getLogger(BasicTest.class);

  @Autowired
  private TranslateService translateService;

  @Autowired
  private Token token;

  /**
   * check translate result
   */
  @Test
  public void testTranslate() {
    TranslateBean translateBean = new TranslateBean();
    translateBean.setTarget("測試");
    translateBean.setSl(Language.TRADITIONAL_CHINESE);
    translateBean.setTl(Language.ENGLISH);
    assertEquals(
        translateService.getTranslateResult(translateBean).getResult(), "test");
  }

  /**
   * check get google token in crawler way
   */
  @Test
  public void testToken() {
    assertNotNull(token.getToken("test"));
  }

}
