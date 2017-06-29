package org.yuwei.model.view;

import java.io.Serializable;

public class TranslateView implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 146797011715581871L;

  private String sl;
  
  private String tl;
  
  private String target;
  
  public TranslateView(String target, String sl, String tl){
    this.target = target;
    this.sl = sl;
    this.tl = tl;
  }

  public String getSl() {
    return sl;
  }

  public void setSl(String sl) {
    this.sl = sl;
  }

  public String getTl() {
    return tl;
  }

  public void setTl(String tl) {
    this.tl = tl;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }
  
}
