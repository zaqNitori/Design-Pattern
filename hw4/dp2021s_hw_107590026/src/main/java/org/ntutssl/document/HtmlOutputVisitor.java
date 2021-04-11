package org.ntutssl.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class HtmlOutputVisitor implements Visitor
{
	private List<Document> docList;

	public HtmlOutputVisitor() 
	{
		docList = new ArrayList<>();
	}

	public void visitParagraph(Paragraph paragraph) 
	{
		docList.add(paragraph);
	}

	public void visitTitle(Title title) 
	{
		docList.add(title);
	}

	public void visitArticle(Article article) 
	{
		docList.add(article);
	}

	public int getListSize() { return docList.size(); }

	public String getResult() 
	{
		String out="";
		for(Document doc:docList)
		{
			if(doc.getClass().equals(Article.class))
				out += recurArticle(doc,1);
			if(doc.getClass().equals(Paragraph.class))
				out += "<p>" + doc.getText() + "</p>\n";
			if(doc.getClass().equals(Title.class))
				out += ("<h1>" + doc.getText() + "</h1>\n");
		}
		return out;
	}

	private String recurArticle(Document article,int layer)
	{
		String out="";
		Iterator<Document> docIter = article.iterator();
		Document doc;

		out += "<article topic=\'" + article.getText() + "\'>\n";
		while(docIter.hasNext())
		{
			doc = docIter.next();
			out += addTab(layer);
			if(doc.getClass().equals(Article.class))
				recurArticle(doc,layer+1);
			if(doc.getClass().equals(Paragraph.class))
				out += "<p>" + doc.getText() + "</p>\n";
			if(doc.getClass().equals(Title.class))
				out += ("<h" + doc.getSize() + ">" + doc.getText() + "</h" + doc.getSize() + ">\n");
		}
		out += "</article>\n";
		return out;
	}

	private String addTab(int layer)
	{
		String out = "";
		while(layer-- > 0) out += "  ";
		return out;
	}

}
