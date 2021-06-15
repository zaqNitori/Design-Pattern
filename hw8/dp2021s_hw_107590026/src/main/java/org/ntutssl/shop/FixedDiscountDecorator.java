package org.ntutssl.shop;

public class FixedDiscountDecorator extends Decorator 
{

	private Double _discount;

	/**
	 * counstructor
	 * @param goods goods to be decorated
	 * @param discount fixed discount, which should be lower than the price of goods
	 */
	public FixedDiscountDecorator(Goods goods, double discount) 
	{ 
		super(goods);
		if(goods.price() < discount)
			throw new ShopException("Discount should be lower than the price of goods");
		_discount = discount;
	}

	public int id() 
	{ 
		return goods.id();
	}

	public double price() 
	{ 
		return goods.price() - _discount;
	}

	public String name() 
	{ 
		return goods.name();
	}

	public String description() 
	{ 
		return goods.description();
	}
}
