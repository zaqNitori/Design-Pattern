package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class AddCommandToArticleTest 
{

    public AddCommandToArticle acta;
    public Article article = new Article("123", 1);
    public Paragraph paragraph = new Paragraph("987");

    @Before
    public void init()
    {
        acta = new AddCommandToArticle(article, paragraph);
    }

    @Test
    public void testExecute()
    {
        assertEquals(0, article.getListSize());
        acta.execute();
        assertEquals(1, article.getListSize());

        Iterator<Document> docIter = article.iterator();
        assertEquals("987", docIter.next().getText());
    }

    @Test
    public void testUndo()
    {
        acta.execute();
        assertEquals(1, article.getListSize());
        acta.undo();
        assertEquals(0, article.getListSize());

        Iterator<Document> docIter = article.iterator();
        assertFalse(docIter.hasNext());
    }

    @Test
    public void testRedo()
    {
        acta.execute();
        assertEquals(1, article.getListSize());
        acta.undo();
        assertEquals(0, article.getListSize());
        acta.redo();
        assertEquals(1, article.getListSize());

        Iterator<Document> docIter = article.iterator();
        assertEquals("987", docIter.next().getText());
    }

}
