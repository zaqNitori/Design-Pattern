package org.ntutssl.document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Article implements Document 
{  
	private String _topic;
	private int _level;
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

	public int getListSize()
	{
		return docList.size();
	}

	@Override
	public void add(Document document) 
	{
		if(document.getClass().equals(Article.class))
		{
			if(document.getLevel() <= _level)
				throw new DocumentException("Invalid Input: The level should be positive or higher than the level of the current article");
		}

		docList.add(document);
	}

	@Override
	public void remove(Document document) 
	{
		docList.remove(document);
	}

	@Override
	public Iterator<Document> iterator() 
	{
		return docList.iterator();
	}

	@Override
	public String toString() 
	{
		String s = "Article\t\ttopic: " + _topic + 
		"\n\t\tlevel: " + _level + "\n";
		return s;
	}
}
