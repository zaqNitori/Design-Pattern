package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class AddCommandToEditorTest 
{

    public AddCommandToEditor acte;
    public Editor editor = new Editor();
    public Paragraph paragraph = new Paragraph("987");

    @Before
    public void init()
    {
        acte = new AddCommandToEditor(editor, paragraph);
    }

    @Test
    public void testExecute()
    {
        assertEquals(0, editor.size());
        acte.execute();
        assertEquals(1, editor.size());

        Iterator<Document> docIter = editor.iterator();
        assertEquals("987", docIter.next().getText());
    }

    @Test
    public void testUndo()
    {
        acte.execute();
        assertEquals(1, editor.size());
        acte.undo();
        assertEquals(0, editor.size());

        Iterator<Document> docIter = editor.iterator();
        assertFalse(docIter.hasNext());
    }

    @Test
    public void testRedo()
    {
        acte.execute();
        assertEquals(1, editor.size());
        acte.undo();
        assertEquals(0, editor.size());
        acte.redo();
        assertEquals(1, editor.size());

        Iterator<Document> docIter = editor.iterator();
        assertEquals("987", docIter.next().getText());
    }

}