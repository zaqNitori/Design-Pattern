package org.ntutssl.document;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class HtmlOutputConsumer implements Consumer<Document> 
{

	private List<String> strList;
	/**
	 * @param result you should add the result into here.
	 * 
	 * example:
	 * this.result.get(0) = "<article topic='topic1'>\n";
	 * this.result.get(1) = "  <h1>title1</h1>\n";
	 * this.result.get(2) = "  <p>paragraph1</p>\n";
	 * this.result.get(3) = "  <h2>title2</h2>\n";
	 * this.result.get(4) = "</article>\n";
	 */
	public HtmlOutputConsumer(List<String> result) 
	{ 
		strList = result;
	}

	public void accept(Document document) 
	{ 
		if(document.getClass().equals(Title.class)) 
			strList.add(outputTitle((Title)document));
		else if(document.getClass().equals(Paragraph.class))
			strList.add(outputParagraph((Paragraph)document));
		else
			strList.add(outputArticle((Article)document, 1));
	}

	private String outputTitle(Title title)
	{
		return "<h" + title.getSize() + ">" + title.getText() + "</h" + title.getSize() + ">";
	}

	private String outputParagraph(Paragraph paragraph)
	{
		return "<p>" + paragraph.getText() + "</p>";
	}

	private String outputArticle(Article article, int layer)
	{
		String s="";
		Document doc;
		Iterator<Document> docIter = article.iterator();

		s = addIndent(layer - 1) + "<article topic=\'" + article.getText() + "\'>";
		strList.add(s);

		while(docIter.hasNext())
		{
			doc = docIter.next();
			if(doc.getClass().equals(Article.class))
				s = outputArticle((Article)doc, layer + 1);
			else if(doc.getClass().equals(Title.class))
				s = addIndent(layer) + outputTitle((Title)doc);
			else
				s = addIndent(layer) + outputParagraph((Paragraph)doc);
			
			strList.add(s);
		}

		return addIndent(layer - 1) + "</article>";
	}

	private String addIndent(int layer)
	{
		String s = "";
		while(layer-- > 0) s += "  ";
		return s;
	}

}