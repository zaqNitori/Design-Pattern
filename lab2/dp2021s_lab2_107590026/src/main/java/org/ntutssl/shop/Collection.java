package org.ntutssl.shop;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Collection implements Goods 
{
  
	private int _id;
	private String _name;
	private String _desc;
	private List<Goods> goodList;

	/**
	 * consturctor
	 * @param id goods id which should not be negative
	 * @param name
	 * @param desc
	 */
	public Collection(int id, String name, String desc) 
	{ 
		_id = id;
		_name = name;
		_desc = desc;
		goodList = new ArrayList<>();
	}

	public int id() 
	{
		return _id;
	}

	public double price() 
	{
		int sum = 0;
		for(Goods goods: goodList)
			sum += goods.price();
		return sum;
	}

	public String name() 
	{
		return _name;
	}

	public String description() 
	{
		String out = _desc;
		for(Goods goods: goodList)
			out += goods.description();
		return out;
	}

	@Override
	public void add(Goods goods) 
	{
		goodList.add(goods);
	}

	@Override
	public Iterator<Goods> iterator() 
	{
		return goodList.iterator();
	}

}