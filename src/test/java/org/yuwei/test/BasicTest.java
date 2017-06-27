package org.yuwei.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yuwei.config.AppConfig;
import org.yuwei.param.Language;
import org.yuwei.service.TranslateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class BasicTest {

  @Autowired
  private TranslateService translateService;
  
  @Test
  public void testTranslate(){
    String target = "測試";
    String result = translateService.getTranslateResult(target, 
        Language.TRADITIONAL_CHINESE.getValue(), Language.ENGLISH.getValue()).getResult();
    String except = "test";
    assertEquals(result, except);
  }
  
}
