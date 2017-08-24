package org.yuwei.crawler;

import java.util.List;

public interface NyTimesCrawler {
  
  final String NyTimesHomePage = "https://www.nytimes.com";
  
  final String articleCss = "article.story";
  
  List<String> getArticleLinks();
  
  void transformFile(String link);
}
