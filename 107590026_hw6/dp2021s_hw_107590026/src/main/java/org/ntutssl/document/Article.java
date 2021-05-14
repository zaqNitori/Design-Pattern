package org.ntutssl.document;

public class Article implements Document 
{

	private String _topic;
	private int _level;

	public Article(String topic, int level) 
	{ 
		_topic = topic;
		_level = level;
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
	public void add(Document document) { }

	@Override
	public Iterator<Document> iterator() { }

	@Override
	public String toString() { }

}