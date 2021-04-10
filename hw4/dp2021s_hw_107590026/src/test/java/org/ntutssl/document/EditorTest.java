package org.ntutssl.document;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

public class EditorTest 
{ 

	public String msg = "hello";
	public String msg2 = "bye"; 

	@Test
	public void testEditorAddandSize()
	{
		Editor editor = new Editor();

		assertEquals(0,editor.size());
		editor.add(new Paragraph(msg));
		assertEquals(1,editor.size());
		editor.add(new Paragraph(msg2));
		assertEquals(2,editor.size());
	}

	@Test
	public void testEditorIterator()
	{
		Editor editor = new Editor();
		editor.add(new Paragraph(msg));
		editor.add(new Paragraph(msg2));

		Iterator<Document> iterator = editor.iterator();
		assertEquals(msg,iterator.next().getText());
		assertEquals(msg2,iterator.next().getText());

	}

}