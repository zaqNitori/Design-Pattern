package org.ntutssl.document;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TitleTest 
{ 
    @Rule
    public ExpectedException expection = ExpectedException.none();
    
    public String text = "hello";
    public int size = 2;
    public Title title = new Title(text, size);

    @Test
    public void testGetText()
    {
        assertEquals(text, title.getText());
    }

    @Test
    public void testGetSize()
    {
        assertEquals(size, title.getSize());
    }

    @Test
    public void testGetLevel()
    {
        expection.expect(DocumentException.class);
        expection.expectMessage("Invalid action: getLevel.");
        
        title.getLevel();
    }

    @Test
    public void testAdd()
    {
        expection.expect(DocumentException.class);
        expection.expectMessage("Invalid action: add.");
        
        title.add(new Title(text, size));
    }

    @Test
    public void testIterator()
    {
        Iterator<Document> docIter = title.iterator();
        assertFalse(docIter.hasNext());
    }
    
    @Test
    public void testNext()
    {
        Iterator<Document> docIter = title.iterator();
        expection.expect(NoSuchElementException.class);
        expection.expectMessage("No Such Element!");

        docIter.next();
    }

    @Test
    public void testToString()
    {
        assertEquals("Title\t\ttext: " + text + 
		"\n\t\tsize: " + size + "\n", title.toString());
    }


}