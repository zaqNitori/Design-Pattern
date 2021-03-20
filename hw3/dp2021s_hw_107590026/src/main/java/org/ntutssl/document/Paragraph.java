package org.ntutssl.document;

public class Paragraph implements Document {
  
  private String paragraph;

  public Paragraph(String text) 
  {  
    paragraph = text;
  }

  public String getText() 
  {  
    return paragraph;
  }
}
