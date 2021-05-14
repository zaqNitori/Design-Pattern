package org.ntutssl.document;

import java.util.Iterator;

public class NullIterator implements Iterator<Document> 
{
  
	@Override
	public boolean hasNext() 
	{ 
		return false;
	}

	@Override
	public Document next() 
	{ 
		return null;
	}

}