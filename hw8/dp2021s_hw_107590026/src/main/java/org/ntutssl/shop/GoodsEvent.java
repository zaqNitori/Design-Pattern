package org.ntutssl.shop;

public class GoodsEvent extends Event<Goods> 
{

  	private EventManager eventManager;
	private EventListener listener;

	public GoodsEvent(EventType eventType, Goods goods, int count) 
	{ 
		super(eventType, goods);
		this.eventManager = EventManager.getInstance();
		this.eventManager.subscribe(eventType, listener);
	}

	@Override
	public int count() 
	{ 
		return count();
	}
}
