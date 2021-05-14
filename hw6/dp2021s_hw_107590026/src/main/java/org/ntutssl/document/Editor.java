package org.ntutssl.document;

import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

public class Editor
{

	private List<Document> docList;

	public Editor() 
	{ 
		docList = new ArrayList<>();
	}

	public void importDocumentFromJsonFile(String filePath) { }

	public void exportDocumentAsHtmlFile(String outputPath) { }

	public void findContent(String target) { }

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