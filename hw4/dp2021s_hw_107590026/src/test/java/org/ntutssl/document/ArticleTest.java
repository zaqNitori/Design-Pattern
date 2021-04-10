package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class ArticleTest 
{ 
    @Rule
    public ExpectedException expected = ExpectedException.none();

    public String s = "hello";

    @Test
    public void testArticleGetText()
    {
        Article article = new Article(s,1);

        assertEquals(s,article.getText());
    }

    @Test
	public void testArticleGetLevel()
	{
		Article article = new Article(s,1);

		assertEquals(1,article.getLevel());
	}

    @Test
	public void testArticleAdd()
	{
		Article article = new Article(s,1);

        assertEquals(0,article.getListSize());
		article.add(new Paragraph("hi"));
        assertEquals(1,article.getListSize());
	}

    @Test
	public void testArticleGetSize()
	{
		Article article = new Article(s,1);

		expected.expect(DocumentException.class);
		expected.expectMessage("Invalid action: getSize.");
		article.getSize();
	}

    @Test
	public void testArticleIterator()
	{
		Article article = new Article(s,1);

        String title = "title", paragraph = "paragraph";
        article.add(new Title(title,title.length()));
        article.add(new Paragraph(paragraph));

        Iterator<Document> iterator = article.iterator();
        assertEquals(title,iterator.next().getText());
        assertEquals(paragraph,iterator.next().getText());
	}

    @Test
	public void testArticletoString()
	{
		Article article = new Article(s,1);

		assertEquals("Article\t\t topic: " + s + 
		"\n\t\tlevel: " + 1 ,article.toString());
	}

	@Test
	public void testArticleAddSmallerLevel()
	{
		Article article = new Article(s,2);

        Article article2 = new Article(s,1);

        /*expected.expect(DocumentException.class);
		expected.expectMessage("Invalid Input: The level should be positive or higher than the level of the current article.");
		article.add(article2);*/
	}

}
