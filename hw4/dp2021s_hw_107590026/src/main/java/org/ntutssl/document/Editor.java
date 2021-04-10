package org.ntutssl.document;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Editor 
{  
	private List<Document> docList;

	public Editor() 
	{
		docList = new ArrayList<>();
	}

	public void add(Document document) 
	{
		docList.add(document);
	}

	public int size() 
	{
		return docList.size();
	}

	public Iterator<Document> iterator() 
	{
		Iterator<Document> docIter = docList.iterator();
		return docIter;
	}
}
