package org.ntutssl.document;

import java.util.List;
import java.util.function.Consumer;

public class FindContentConsumer implements Consumer<Document> 
{

	private List<Document> docList;
	private String _target;
	/**
	 * @param result you should add the document which contains `target` into here.
	 */
	public FindContentConsumer(List<Document> result, String target) 
	{ 
		docList = result;
		_target = target;
	}

	public void accept(Document document) 
	{ 
		
	}

}