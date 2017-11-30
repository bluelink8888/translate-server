package org.yuwei.model.bean;

import java.io.Serializable;

import com.github.bluelink8888.constant.Language;

public class TranslateBean implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 400865575895965119L;

  
  private String target;
  
  private Language sl;
  
  private Language tl;

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public Language getSl() {
    return sl;
  }

  public void setSl(Language sl) {
    this.sl = sl;
  }

  public Language getTl() {
    return tl;
  }

  public void setTl(Language tl) {
    this.tl = tl;
  }
  
}
