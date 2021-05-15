package org.ntutssl.document;

import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ParagraphTest 
{ 
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    public String text = "hello";
    public Paragraph paragraph = new Paragraph(text);

    @Test
    public void testGetText()
    {
        assertEquals(text, paragraph.getText());
    }

    @Test
    public void testGetSize()
    {
        exception.expect(DocumentException.class);
        exception.expectMessage("Invalid action: getSize.");
        
        paragraph.getSize();
    }

    @Test
    public void testGetLevel()
    {
        exception.expect(DocumentException.class);
        exception.expectMessage("Invalid action: getLevel.");
        
        paragraph.getLevel();
    }

    @Test
    public void testAdd()
    {
        exception.expect(DocumentException.class);
        exception.expectMessage("Invalid action: add.");
        
        paragraph.add(new Paragraph(text));
    }

    @Test
    public void testIterator()
    {
        Iterator<Document> docIter = paragraph.iterator();
        assertFalse(docIter.hasNext());
    }

    @Test
    public void testToString()
    {
        assertEquals("Paragraph\ttext: " + text + "\n"
        , paragraph.toString());
    }
}