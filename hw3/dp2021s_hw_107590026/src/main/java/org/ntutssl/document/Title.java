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
}