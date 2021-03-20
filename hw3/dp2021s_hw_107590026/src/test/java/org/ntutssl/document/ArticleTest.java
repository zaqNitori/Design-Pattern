package org.ntutssl.document;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;


public class ArticleTest 
{  
   
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void testAdd()
    {
        Article article = new Article("game",0);
        article.add(new Paragraph("paragraph1"));
        
        assertEquals(article.getSize(), 1);
        assertEquals(article.getContent(0).getText(), "paragraph1");
    }

    @Test
    public void testCorrect_addAndgetContent()
    {
        Article article = new Article("game",3);
        article.add(new Article("book",5));
        assertEquals(article.getSize(), 1);
        assertEquals(article.getContent(0).getText(), "book");

        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid add: addLevel less than primitive level.");
        article.add(new Article("music",2));
    }

    @Test
    public void testCorrect_getText()
    {
        Article article = new Article("game",0);

        assertEquals(article.getText(), "game");
    }

    @Test
    public void testCorrect_getLevel()
    {
        Article article = new Article("game",0);

        assertEquals(article.getLevel(), 0);
    }

}
