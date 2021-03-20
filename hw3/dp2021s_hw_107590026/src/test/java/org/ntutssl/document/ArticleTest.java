package org.ntutssl.document;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class ArticleTest 
{  
   
    @Test
    public void testCorrect_addAndgetContent()
    {
        Article article = new Article("game",0);
        article.add(new Paragraph("paragraph1"));
        
        assertEquals(article.getSize(), 1);
        assertEquals(article.getContent(0).getText(), "paragraph1");
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
