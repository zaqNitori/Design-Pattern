package org.ntutssl.document;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HtmlOutputConsumerTest 
{ 

    public List<Document> docList = new ArrayList<>();
    public Title title = new Title("title", 1);
    public Paragraph paragraph = new Paragraph("paragraph");
    public Article article = new Article("article",1);

    @Before
    public void init()
    {
        article.add(paragraph);
        docList.add(title);
        docList.add(article);
    }

    @Test
    public void testOutput()
    {
        List<String> result = new ArrayList<>();
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(result);
    
        docList.forEach((doc) -> hoc.accept(doc));

        assertEquals("<h1>" + title.getText() + "</h1>", result.get(0));
        assertEquals("<article topic=\'" + article.getText() + "\'>", result.get(1));
        assertEquals("  <p>" + paragraph.getText() + "</p>", result.get(2));
        assertEquals("</article>", result.get(3));
    }

}