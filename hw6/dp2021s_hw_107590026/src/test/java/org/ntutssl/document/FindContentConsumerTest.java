package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FindContentConsumerTest 
{ 
    
    public String target = "hello";
    public List<Document> docList = new ArrayList<>();

    @Before
    public void init()
    {
        Title title = new Title("hello123", 2);
        Title title2 = new Title("ne110123", 3);
        Paragraph paragraph = new Paragraph("helloParagraph");
        Paragraph paragraph2 = new Paragraph("nell0321");
        Article article = new Article("123hello321", 1);
        article.add(title2);
        article.add(paragraph);
        docList.add(title);
        docList.add(paragraph2);
        docList.add(article);
    }

    @Test
    public void testAccept()
    {
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, target);
        docList.forEach((doc) -> fcc.accept(doc));
        
        assertEquals("hello123", result.get(0).getText());
        assertEquals("123hello321", result.get(1).getText());
        assertEquals("helloParagraph", result.get(2).getText());
    }

}