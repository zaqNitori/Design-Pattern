package org.ntutssl.document;

public class Title implements Document {
  
  private String title;

  public Title(String text) 
  {  
    title = text;
  }

  public String getText() 
  {  
    return title;
  }

  public Document getContent(int index) 
  {
    throw new DocumentException("Invalid Behavior: getContent.");
  }
}