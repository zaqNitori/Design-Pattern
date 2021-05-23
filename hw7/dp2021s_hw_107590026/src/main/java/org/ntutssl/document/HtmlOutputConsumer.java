package org.ntutssl.document;

import java.util.List;
import java.util.function.Consumer;

import java.util.Iterator;

public class HtmlOutputConsumer implements Consumer<Document> 
{
	
	private List<String> strList;

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
		return "<h" + title.getSize() + ">" + title.getText() + "</h" + title.getSize() + ">\n";
	}

	private String outputParagraph(Paragraph paragraph)
	{
		return "<p>" + paragraph.getText() + "</p>\n";
	}

	private String outputArticle(Article article, int layer)
	{
		String s="";
		Document doc;
		Iterator<Document> docIter = article.iterator();

		strList.add( (addIndent(layer - 1) + "<article topic=\'" + article.getText() + "\'>\n") );

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

		return (addIndent(layer - 1) + "</article>\n");
	}

	private String addIndent(int layer)
	{
		String s = "";
		while(layer-- > 0) s += "  ";
		return s;
	}


}
