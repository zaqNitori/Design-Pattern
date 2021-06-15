package org.ntutssl.shop;

public class GoodsEvent extends Event<Goods> 
{

  	private int _count;

	public GoodsEvent(EventType eventType, Goods goods, int count) 
	{ 
		super(eventType, goods);
		_count = count;
	}

	@Override
	public int count() 
	{ 
		return _count;
	}
}
