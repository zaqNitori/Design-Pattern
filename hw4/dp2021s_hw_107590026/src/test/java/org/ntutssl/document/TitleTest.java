package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class TitleTest 
{ 

	@Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void testTitleGetText()
    {
        String msg = "hello";
        Title title = new Title(msg,msg.length());

        assertEquals(msg,title.getText());
    }

	@Test
	public void testTitleGetSize()
	{
		String msg = "hello";
        Title title = new Title(msg,msg.length());

        assertEquals(msg.length(),title.getSize());
	}

	@Test
	public void testTitletoString()
	{
		String msg = "hello";
		Title title = new Title(msg,msg.length());

		assertEquals("Title\t\t text: " + msg + 
		"\n\t\tsize: " + msg.length(),title.toString());
	}

	@Test
	public void testTitleAdd()
	{
		Title title = new Title("123",3);

		expected.expect(DocumentException.class);
		expected.expectMessage("Invalid action: add.");
		title.add(new Title("123",3));
	}

	@Test
	public void testTitleGetLevel()
	{
		Title title = new Title("123",3);

		expected.expect(DocumentException.class);
		expected.expectMessage("Invalid action: getLevel.");
		title.getLevel();
	}

	@Test
	public void testTitleIterator()
	{
		Title title = new Title("123",3);

		assertFalse(title.iterator().hasNext());

	}

}
