package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class DocumentBuilderTest 
{ 

    public String text = "hello";
    public String text2 = "hi";
    public String text3 = "Lai";
    public DocumentBuilder documentBuilder = new DocumentBuilder();
    
    @Test
    public void testBuildTitle()
    {
        documentBuilder.buildTitle(text, 1);

        assertEquals(text, documentBuilder.getResult().getText());
    }

    @Test
    public void testBuildParagraph()
    {
        documentBuilder.buildParagraph(text);

        assertEquals(text, documentBuilder.getResult().getText());
    }

    @Test
    public void testBuildArticle()
    {
        documentBuilder.startBuildArticle(text, 1);
        documentBuilder.endBuildArticle();

        assertEquals(text, documentBuilder.getResult().getText());
    }

    @Test
    public void testBuildDocumentInArticle()
    {
        documentBuilder.startBuildArticle(text, 1);
        documentBuilder.buildTitle(text2, 2);
        documentBuilder.buildParagraph(text3);
        documentBuilder.endBuildArticle();

        Iterator<Document> docIter = documentBuilder.getResult().iterator();

        assertEquals(text, documentBuilder.getResult().getText());
        assertEquals(text2, docIter.next().getText());
        assertEquals(text3, docIter.next().getText());
    }

}