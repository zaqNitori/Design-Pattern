package org.ntutssl.document;

import java.util.List;
import java.util.ArrayList;

public class Article implements Document 
{  

  private List<Document> docList;
  private String topic;
  private int level;

  public Article(String topic, int level) 
  {  
    docList = new ArrayList<>();
    this.topic = topic;
    this.level = level;
    //docList.add(new Title(topic));
  }

  public int getSize() { return docList.size(); }

  public Document getContent(int index) { return docList.get(index); }

  public String getText() { return topic; }

  @Override
  public int getLevel() { return level; }
  
  @Override
  public void add(Document document) 
  { 
    if(document.getClass().equals(Article.class))
    {
      if(document.getLevel() < level)
        throw new DocumentException("Invalid add: addLevel less than primitive level.");
    }
    docList.add(document); 
  }
}
