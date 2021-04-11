package org.ntutssl.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class FindContentVisitor implements Visitor
{  
	private List<Document> docList;
	private List<Document> targetList;
	private String _target;

	public FindContentVisitor(String target) 
	{
		_target = target.toLowerCase();
		docList = new ArrayList<>();
		targetList = new ArrayList<>();
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

		Iterator<Document> docIter = article.iterator();
		while(docIter.hasNext())
			docIter.next().accept(this);
	}

	public int getListSize() { return docList.size(); }

	public List<Document> getResult() 
	{
		for(Document doc:docList)
		{
			if(doc.getText().matches(_target))
				targetList.add(doc);
			else if(doc.getText().toLowerCase().indexOf(_target) != -1 && _target.length() > 0)
				targetList.add(doc);
		}
		return targetList;
	}
}
