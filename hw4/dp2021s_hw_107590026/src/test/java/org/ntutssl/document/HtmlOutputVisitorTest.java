package org.ntutssl.document;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class HtmlOutputVisitorTest 
{ 
    public String msg1 = "hello1";
    public String msg2 = "hello2";
    public String msg3 = "bye3";

    @Test
    public void testVisit()
    {
        HtmlOutputVisitor hoVisitor = new HtmlOutputVisitor();
        Title title = new Title(msg1,msg1.length());
        Paragraph paragraph = new Paragraph(msg2);
        Article article = new Article(msg3,1);

        assertEquals(0,hoVisitor.getListSize());
        hoVisitor.visitTitle(title);
        assertEquals(1,hoVisitor.getListSize());
        hoVisitor.visitParagraph(paragraph);
        assertEquals(2,hoVisitor.getListSize());
        hoVisitor.visitArticle(article);
        assertEquals(3,hoVisitor.getListSize());
    }

    @Test
    public void testGetResult()
    {
        HtmlOutputVisitor hoVisitor = new HtmlOutputVisitor();
        Title title = new Title(msg1,msg1.length());
        Paragraph paragraph = new Paragraph(msg2);

        hoVisitor.visitTitle(title);
        hoVisitor.visitParagraph(paragraph);
        
        System.out.println(hoVisitor.getResult());

        assertEquals("<h1>hello1</h1>\n<p>hello2</p>\n"
        ,hoVisitor.getResult());
    }
}
