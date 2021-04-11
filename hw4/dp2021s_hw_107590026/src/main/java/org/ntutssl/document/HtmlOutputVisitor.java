package org.ntutssl.document;

import java.util.Iterator;

public class HtmlOutputVisitor implements Visitor
{
	private String outputString="";

	public HtmlOutputVisitor() 
	{

	}

	public void visitParagraph(Paragraph paragraph) 
	{
		outputString += "<p>" + paragraph.getText() + "</p>\n";
	}

	public void visitTitle(Title title) 
	{
		outputString += ("<h" + title.getSize() + ">" + title.getText() + "</h" + title.getSize() + ">\n");
	}

	public void visitArticle(Article article) 
	{
		outputString += recurArticle(article,1);
	}

	public String getResult() 
	{
		return outputString;
	}

	private String recurArticle(Article article,int layer)
	{
		String out="";
		Iterator<Document> docIter = article.iterator();
		Document doc;

		out += "<article topic='" + article.getText() + "'>\n";
		while(docIter.hasNext())
		{
			doc = docIter.next();
			out += addTab(layer);
			if(doc.getClass().equals(Article.class))
				out += recurArticle((Article)doc,layer+1);
			else if(doc.getClass().equals(Paragraph.class))
				out += "<p>" + doc.getText() + "</p>\n";
			else if(doc.getClass().equals(Title.class))
				out += ("<h" + doc.getSize() + ">" + doc.getText() + "</h" + doc.getSize() + ">\n");
		}
		out += addTab(layer-1) + "</article>\n";
		return out;
	}

	private String addTab(int layer)
	{
		String out = "";
		while(layer-- > 0) out += "  ";
		return out;
	}

}
