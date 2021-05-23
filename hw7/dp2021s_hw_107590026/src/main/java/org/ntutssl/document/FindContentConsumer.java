package org.ntutssl.document;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class FindContentConsumer implements Consumer<Document> 
{
	private List<Document> docList;
	private String _target;

	public FindContentConsumer(List<Document> result, String target) 
	{
		docList = result;
		_target = target.toLowerCase();
	}

	public void accept(Document document) 
	{
		if(document.toString().toLowerCase().contains(_target))
			docList.add(document);

		if(document.getClass().equals(Article.class))
		{
			Iterator<Document> docIter = document.iterator();
			while(docIter.hasNext())
				this.accept(docIter.next());
		}
	}
}
