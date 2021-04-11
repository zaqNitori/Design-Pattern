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

        hoVisitor.visitTitle(title);
        hoVisitor.visitParagraph(paragraph);
        hoVisitor.visitArticle(article);
    }

    @Test
    public void testGetResult()
    {
        HtmlOutputVisitor hoVisitor = new HtmlOutputVisitor();
        Article article = new Article(msg3, 1);
        Title title = new Title(msg1,1);
        Paragraph paragraph = new Paragraph(msg2);

        article.add(title);
        article.add(paragraph);

        article.accept(hoVisitor);
        
        System.out.println(hoVisitor.getResult());

        assertEquals("<article topic='bye3'>\n  <h1>hello1</h1>\n  <p>hello2</p>\n</article>\n"
        ,hoVisitor.getResult());
    }
}
