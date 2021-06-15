package org.ntutssl.shop;

public class StringEvent extends Event<String> 
{
	private EventManager eventManager;
	private EventListener listener;

	public StringEvent(EventType eventType, String data) 
	{ 
		super(eventType, data);
		eventManager = EventManager.getInstance();
		eventManager.subscribe(eventType, listener);
	}

	
}
