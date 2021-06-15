package org.ntutssl.shop;

import java.util.Iterator;

public class NullIterator implements Iterator<Goods> 
{

	public Goods next() 
	{ 
		throw new ShopException("No Such Element!");
	}

	public boolean hasNext() 
	{ 
		return false;
	}
}
