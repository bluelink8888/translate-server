package org.yuwei.param;

public enum Language {

  TRADITIONAL_CHINESE("中文","zh-TW"),
  
  ENGLISH("英文","en");
  
  Language(String name, String value){
    this.name = name;
    this.value = value;
  }
  
  private final String name;
  
  private final String value;
  
  public final String getName(){
    return name;
  }
  
  public final String getValue(){
    return value;
  }
  
}
