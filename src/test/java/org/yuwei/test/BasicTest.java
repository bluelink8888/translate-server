package org.yuwei.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yuwei.config.AppConfig;
import org.yuwei.model.enums.Language;
import org.yuwei.model.view.TranslateView;
import org.yuwei.service.TranslateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class BasicTest {

  @Autowired
  private TranslateService translateService;
  
  @Test
  public void testTranslate(){
    TranslateView translateView = new TranslateView(); 
    translateView.setTarget("測試");
    translateView.setSl(Language.TRADITIONAL_CHINESE.getValue());
    translateView.setTl(Language.ENGLISH.getValue());
    assertEquals(translateService.getTranslateResult(translateView).getResult(), "test");
  }
  
}
