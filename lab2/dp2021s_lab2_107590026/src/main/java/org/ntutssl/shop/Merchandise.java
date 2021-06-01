package org.ntutssl.shop;

public class Merchandise implements Goods 
{

	private int _id;
	private String _name;
	private String _desc;
	private double _price;

	/**
	 * constructor
	 * @param id goods id which should not be negative
	 * @param name
	 * @param desc
	 * @param price goods price which should not be negative
	 */
	public Merchandise(int id, String name, String desc, double price) 
	{ 
		_id = id;
		_name = name;
		_desc = desc;
		_price = price;
	}

	public int id() 
	{
		return _id;
	}

	public double price() 
	{
		return _price;
	}

	public String name() 
	{
		return _name;
	}

	public String description() 
	{
		return _desc;
	}

}