package org.ntutssl.document;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class FindContentVisitorTest 
{ 
    public String msg1 = "hello1";
    public String msg2 = "Hello2";
    public String msg3 = "bye3";

    @Test
    public void testVisit()
    {
        FindContentVisitor fdVisitor = new FindContentVisitor("hello");
        Title title = new Title(msg1,msg1.length());
        Paragraph paragraph = new Paragraph(msg2);
        Article article = new Article(msg3,1);

        assertEquals(0,fdVisitor.getListSize());
        fdVisitor.visitTitle(title);
        assertEquals(1,fdVisitor.getListSize());
        fdVisitor.visitParagraph(paragraph);
        assertEquals(2,fdVisitor.getListSize());
        fdVisitor.visitArticle(article);
        assertEquals(2,fdVisitor.getListSize());
    }

    @Test
    public void testGetResult()
    {
        FindContentVisitor fdVisitor = new FindContentVisitor("hello");
        Title title = new Title(msg1,msg1.length());
        Paragraph paragraph = new Paragraph(msg2);      //uppercase can also be matched.
        Article article = new Article(msg3,1);

        fdVisitor.visitTitle(title);
        fdVisitor.visitParagraph(paragraph);
        fdVisitor.visitArticle(article);
        List<Document> docList = new ArrayList<>();
        docList = fdVisitor.getResult();

        assertEquals(2,docList.size());
        assertEquals(msg1,docList.get(0).getText());
        assertEquals(msg2,docList.get(1).getText());
    }
}
