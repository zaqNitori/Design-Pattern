package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class ParagraphTest 
{ 
    @Rule
    public ExpectedException expected = ExpectedException.none();

    public String s = "hello";

    @Test
    public void testParagraphGetText()
    {
        Paragraph paragraph = new Paragraph(s);

        assertEquals(s,paragraph.getText());
    }

    @Test
	public void testParagraphGetLevel()
	{
		Paragraph paragraph = new Paragraph(s);

		expected.expect(DocumentException.class);
		expected.expectMessage("Invalid action: getLevel.");
		paragraph.getLevel();
	}

    @Test
	public void testParagraphAdd()
	{
		Paragraph paragraph = new Paragraph(s);

		expected.expect(DocumentException.class);
		expected.expectMessage("Invalid action: add.");
		paragraph.add(new Paragraph("hi"));
	}

    @Test
	public void testParagraphGetSize()
	{
		Paragraph paragraph = new Paragraph(s);

		expected.expect(DocumentException.class);
		expected.expectMessage("Invalid action: getSize.");
		paragraph.getSize();
	}

    @Test
	public void testParagraphIterator()
	{
		Paragraph paragraph = new Paragraph(s);

		assertFalse(paragraph.iterator().hasNext());

	}

    @Test
	public void testParagraphtoString()
	{
		Paragraph paragraph = new Paragraph(s);

		assertEquals("Paragraph\ttext: " + s + "\n",paragraph.toString());
	}

}
