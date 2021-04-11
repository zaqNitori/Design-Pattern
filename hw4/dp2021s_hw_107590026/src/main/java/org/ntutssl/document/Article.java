package org.ntutssl.document;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Article implements Document 
{
  
	private int _level;
	private String _topic;
	private List<Document> docList;

	public Article(String topic, int level) 
	{
		_topic = topic;
		_level = level;
		docList = new ArrayList<>();
	}

	public String getText() 
	{
		return _topic;
	}

	@Override
	public int getLevel() 
	{
		return _level;
	}
  
	@Override
	public void add(Document document) 
	{
		if(document.getClass().equals(Article.class))
			if(document.getLevel() <= _level)
			{
				System.out.print("Invalid Input: The level should be positive or higher than the level of the current article.\n");
				return;
			}

		docList.add(document);
	}

	public int getListSize() { return docList.size(); }

	@Override
	public Iterator<Document> iterator() 
	{
		return docList.iterator();
	}

	@Override
	public void accept(Visitor visitor) 
	{
		visitor.visitArticle(this);
	}

	@Override
	public String toString()
	{
		String s = "Article\t\t topic: " + _topic + 
		"\n\t\tlevel: " + _level;
		return s;
	}
}
