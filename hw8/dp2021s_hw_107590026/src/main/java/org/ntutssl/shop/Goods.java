package org.ntutssl.shop;

import java.util.Iterator;

public interface Goods 
{
	public int id();
	public double price();
	public String name();
	public String description();
	public default void add(Goods goods) 
	{
		throw new ShopException("invalid operation.");
	}

	public default Iterator<Goods> iterator() 
	{
		return new NullIterator();
	}
}
