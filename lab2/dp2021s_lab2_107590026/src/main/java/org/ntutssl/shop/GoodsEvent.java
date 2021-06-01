package org.ntutssl.shop;

public class GoodsEvent extends Event<Goods> 
{
	private EventManager eventManager;
	private EventListener listener;
	private EventType _type;
	private Goods _goods;
	private int _count;

	public GoodsEvent(EventType eventType, Goods goods, int count) 
	{ 
		super(eventType, goods);
		this.eventManager = EventManager.getInstance();
		this.eventManager.subscribe(eventType, listener);
		_type = eventType;
		_goods = goods;
		_count = count;
	}

	@Override
	public int count() 
	{ 
		return count();
	}
}
