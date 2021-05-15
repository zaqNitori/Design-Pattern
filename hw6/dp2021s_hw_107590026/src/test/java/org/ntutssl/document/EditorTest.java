package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
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

}