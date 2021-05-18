package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class EditorTest 
{ 

    public String filePath = "input/test_input.json";
    public String text = "hello";
    public Editor editor = new Editor();
    Title title = new Title("I'm a simple title", 1);
    Paragraph paragraph = new Paragraph("I'm a simple paragraph");
    Article article = new Article("I'm a simple article", 1);

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
        editor.add(new Paragraph(text));
        Iterator<Document> docIter = editor.iterator();

        while(docIter.hasNext())
        {
            assertEquals(text, docIter.next().getText());
        }
    }

    @Test
    public void testImportDocument()
    {
        editor.importDocumentFromJsonFile(filePath);
    
        Iterator<Document> docIter = editor.iterator();
        
        assertEquals(title.toString(), docIter.next().toString());
        assertEquals(paragraph.toString(), docIter.next().toString());
        assertEquals(article.toString(), docIter.next().toString());
        
    }

    @Test
    public void testFindContent()
    {
        
        editor.importDocumentFromJsonFile(filePath);
        editor.findContent("simple");

        List<Document> docTarget = editor.getFindContent();

        assertEquals(title.toString(), docTarget.get(0).toString());
        assertEquals(paragraph.toString(), docTarget.get(1).toString());
        assertEquals(article.toString(), docTarget.get(2).toString());
    }

    @Test
    public void testExport()
    {
        editor.importDocumentFromJsonFile("input/sample_input.json");
        editor.exportDocumentAsHtmlFile("1230");
    }

}