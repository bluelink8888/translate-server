package org.yuwei.crawler.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.yuwei.crawler.NyTimesCrawler;

@Service
public class NyTimesCrawlerImpl implements NyTimesCrawler {

  private final static Logger logger = Logger.getLogger(NyTimesCrawlerImpl.class);
  
  @Override
  public List<String> getArticleLinks() {
    
    List<String> result = null;
    
    try {
      Document doc = Jsoup.connect(NyTimesHomePage).get();
      Elements elements = doc.select(articleCss).select("a[href]");
      result = elements.stream().map(e->e.attr("href"))
          .filter(a-> a.contains(NyTimesHomePage + "/" + Integer.toString(LocalDateTime.now().getYear())))
          .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error(e);
    }
    return result;
  }

  @Override
  public void transformFile(String link) {
  }

}
