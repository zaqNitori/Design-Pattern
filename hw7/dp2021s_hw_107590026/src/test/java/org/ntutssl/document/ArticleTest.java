package org.ntutssl.document;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArticleTest 
{ 
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    public String text = "hello";
    public int level = 2;
    public Article article = new Article(text, level);
    public Article article2 = new Article(text, level+1);
    public Article article3 = new Article(text, level);

    @Test
    public void testGetText()
    {
        assertEquals(text, article.getText());
    }

    @Test
    public void testGetLevel()
    {
        assertEquals(level, article.getLevel());
    }

    @Test
    public void testAdd()
    {
        article.add(article2);
        assertEquals(1, article.getListSize());
    }

    @Test
    public void testAddArticleWithSameLevel()
    {
        exception.expect(DocumentException.class);
        exception.expectMessage("Invalid Input: The level should be positive or higher than the level of the current article");
        
        article.add(article3);
    }

    @Test
    public void testIterator()
    {
        article.add(article2);
        Iterator<Document> docIter = article.iterator();
        while(docIter.hasNext())
        {
            assertEquals(text, docIter.next().getText());
        }
    }

    @Test
    public void testToString()
    {
        assertEquals("Article\t\ttopic: " + text + 
		"\n\t\tlevel: " + level + "\n", article.toString());
    }

    @Test
    public void testGetSize()
    {
        exception.expect(DocumentException.class);
        exception.expectMessage("Invalid action: getSize.");

        article.getSize();
    } 

    @Test
    public void testRemoveContains()
    {
        article.add(article2);
        assertEquals(1, article.getListSize());

        article.remove(article2);
        assertEquals(0, article.getListSize());
    }

    @Test
    public void testRemoveUnContains()
    {
        article.add(article2);
        assertEquals(1, article.getListSize());

        article.remove(article3);
        assertEquals(1, article.getListSize());
    }

}