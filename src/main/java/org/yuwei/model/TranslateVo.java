package org.yuwei.model;

import java.io.Serializable;

public class TranslateVo implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -1817935325973269020L;

  
  /**
   * store user want translate
   */
  private String target;

  /**
   * store translate result
   */
  private String result;
  
  /**
   * record query times
   */
  private Integer time;

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }
  
  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
  
  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

    
}
