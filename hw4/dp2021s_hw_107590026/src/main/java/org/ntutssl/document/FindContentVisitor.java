package org.ntutssl.document;

import java.util.List;
import java.util.ArrayList;

public class FindContentVisitor implements Visitor
{  
	private List<Document> docList;
	private String _target;

	public FindContentVisitor(String target) 
	{
		_target = target.toLowerCase();
		docList = new ArrayList<>();
	}

	public void visitParagraph(Paragraph paragraph) 
	{
		if(paragraph.getText().toLowerCase().indexOf(_target) != -1)
			docList.add(paragraph);
	}

	public void visitTitle(Title title) 
	{
		if(title.getText().toLowerCase().indexOf(_target) != -1)
			docList.add(title);
	}

	public void visitArticle(Article article) 
	{
		if(article.getText().toLowerCase().indexOf(_target) != -1)
			docList.add(article);
	}

	public int getListSize() { return docList.size(); }

	public List<Document> getResult() 
	{
		return docList;
	}
}
