package org.ntutssl.document;

public interface Document 
{  

    public String getText();

    default public int getLevel()
    {
        throw new DocumentException("Invalid Behavior: getLevel");
    }

    default public void add(Document document)
    {
        throw new DocumentException("Invalid Behavior: add");
    }


}
