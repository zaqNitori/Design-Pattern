package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class ParagraphTest 
{  
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void testCorrect_getText()
    {
        Paragraph p = new Paragraph("Hello");
        
        assertEquals(p.getText(),"Hello");
    }

    @Test
    public void testUse_addMethod()
    {
        Paragraph p = new Paragraph("123");

        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid Behavior: add.");
        p.add(new Paragraph("456"));
    }

    @Test
    public void testUse_getLevelMethod()
    {
        Paragraph p = new Paragraph("123");

        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid Behavior: getLevel.");
        p.getLevel();
    }

    @Test
    public void testUse_getContentMethod()
    {
        Paragraph p = new Paragraph("123");

        expected.expect(DocumentException.class);
        expected.expectMessage("Invalid Behavior: getContent.");
        p.getContent(1);
    }

}
