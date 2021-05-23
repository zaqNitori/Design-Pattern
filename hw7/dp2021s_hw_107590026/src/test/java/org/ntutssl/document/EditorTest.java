package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;


public class EditorTest 
{ 

    public String filePath = "input/sample_input.json";
    public String outputPath = "output/test_output.html";
    public String text = "hello";
    public Editor editor = new Editor();

    Title title = new Title("Object-Oriented Programming", 1);
    Paragraph paragraph = new Paragraph("This course discusses an overview of the object-oriented programming paradigm.");
    Article article = new Article("Course Object-Oriented Programming", 1);

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
        editor.findContent("object-oriented");

        List<Document> docTarget = editor.getFindContent();

        assertEquals(title.toString(), docTarget.get(0).toString());
        assertEquals(paragraph.toString(), docTarget.get(1).toString());
        assertEquals(article.toString(), docTarget.get(2).toString());
    }

    @Test
    public void testExport()
    {
        editor.importDocumentFromJsonFile(filePath);
        editor.exportDocumentAsHtmlFile(outputPath);
    
        try(Scanner sc = new Scanner(new File(outputPath)))
        {
            assertEquals("<h1>Object-Oriented Programming</h1>", sc.nextLine());
            assertEquals("<p>This course discusses an overview of the object-oriented programming paradigm.</p>", sc.nextLine());
            assertEquals("<article topic='Course Object-Oriented Programming'>", sc.nextLine());
            assertEquals("  <h2>Information</h2>", sc.nextLine());
            assertEquals("  <p>Professor: YC Cheng</p>", sc.nextLine());
            assertEquals("  <article topic='Introduction'>", sc.nextLine());
            assertEquals("    <h3>What you will learn in this class?</h3>", sc.nextLine());
            assertEquals("    <p>problem solving, C++, engineering practices</p>", sc.nextLine());
            assertEquals("  </article>", sc.nextLine());
            assertEquals("  <h2>References</h2>", sc.nextLine());
            assertEquals("  <p>https://www.cplusplus.com/</p>", sc.nextLine());
            assertEquals("</article>", sc.nextLine());      
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    
    }

}