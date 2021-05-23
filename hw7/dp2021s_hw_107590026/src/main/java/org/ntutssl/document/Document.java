package org.ntutssl.document;

import java.util.Iterator;

public interface Document 
{  
	public default Iterator<Document> iterator() 
	{
		Iterator<Document> docIter = new NullIterator();
		return docIter;
	}

	public default int getSize() 
	{
		throw new DocumentException("Invalid action: getSize.");
	}

	public default int getLevel() 
	{
		throw new DocumentException("Invalid action: getLevel.");
	}


	public default void add(Document document) 
	{
		throw new DocumentException("Invalid action: add.");
	}

	public default void remove(Document document) 
	{
		throw new DocumentException("Invalid action: remove.");
	}

	public String getText();

	public String toString();
}
