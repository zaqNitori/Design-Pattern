package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class CommandManagerTest 
{

    public CommandManager cmdManager = new CommandManager();
    public Article article = new Article("123", 1);
    public Paragraph paragraph = new Paragraph("987");

    @Test
    public void testExecuteCmd()
    {
        assertEquals(0, article.getListSize());
        cmdManager.executeCmd(new AddCommandToArticle(article, paragraph));
        assertEquals(1, article.getListSize());
    }

    @Test
    public void testUndoCmdWithNoCmd()
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);

        cmdManager.undoCmd();
        assertEquals("No command can be undid.\n", stream.toString());
    }

    @Test
    public void testRedoCmdWithNoCmd()
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);

        cmdManager.redoCmd();
        assertEquals("No command can be redid.\n", stream.toString());
    }

    @Test
    public void testUndoCmd()
    {
        cmdManager.executeCmd(new AddCommandToArticle(article, paragraph));
        assertEquals(1, article.getListSize());
        cmdManager.undoCmd();
        assertEquals(0, article.getListSize());

        Iterator<Document> docIter = article.iterator();
        assertFalse(docIter.hasNext());
    }

    @Test
    public void testRedoCmd()
    {
        cmdManager.executeCmd(new AddCommandToArticle(article, paragraph));
        assertEquals(1, article.getListSize());
        cmdManager.undoCmd();
        assertEquals(0, article.getListSize());
        cmdManager.redoCmd();
        assertEquals(1, article.getListSize());

        Iterator<Document> docIter = article.iterator();
        assertEquals("987", docIter.next().getText());
    }

}