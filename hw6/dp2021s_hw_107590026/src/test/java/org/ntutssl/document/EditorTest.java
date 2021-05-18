package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;


public class EditorTest 
{ 
    public String text = "hello";
    public Editor editor = new Editor();
    public Paragraph paragraph = new Paragraph(text);


    @Test
    public void testAdd()
    {
        assertEquals(0, editor.size());
        editor.add(paragraph);
        assertEquals(1, editor.size());
    }

    @Test
    public void testSize()
    {
        assertEquals(0, editor.size());
        editor.add(paragraph);
        assertEquals(1, editor.size());
    }

    @Test
    public void testIterator()
    {
        editor.add(paragraph);
        Iterator<Document> docIter = editor.iterator();

        while(docIter.hasNext())
        {
            assertEquals(text, docIter.next().getText());
        }
    }

    @Test
    public void testImportDocument()
    {
        editor.importDocumentFromJsonFile("input/test_input.json");
    
        Title title = new Title("I'm a simple title", 1);
        Paragraph paragraph = new Paragraph("I'm a simple paragraph");
        Article article = new Article("I'm a simple article", 1);

        Iterator<Document> docIter = editor.iterator();
        
        assertEquals(title.toString(), docIter.next().toString());
        assertEquals(paragraph.toString(), docIter.next().toString());
        assertEquals(article.toString(), docIter.next().toString());
        
    }

    @Test
    public void testFindContent()
    {
        Title title = new Title("Hello world!", 1);

        assertTrue(title.getText().contains("o w"));
    }

}