package org.ntutssl.shop;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NullIterator implements Iterator<Goods> 
{
	public Goods next() 
	{ 
		throw new NoSuchElementException("No Such Element!");
	}

	public boolean hasNext() 
	{ 
		return false;
	}
}
