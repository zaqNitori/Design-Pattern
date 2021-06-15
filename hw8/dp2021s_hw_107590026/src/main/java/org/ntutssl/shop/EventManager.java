package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager 
{

	private static volatile EventManager instance = null;
	private Map<EventType, List<EventListener>> subscribers;

	public EventManager()
	{
		subscribers = new HashMap<>();
	}

	public void reset()
	{
		subscribers.clear();
	}

	public static EventManager getInstance() 
	{
		// lazy initialization
		// double check locking
		if (instance == null) 
		{
			synchronized(EventManager.class) 
			{
				if (instance == null) 
				{
					instance = new EventManager();
				}
			}
		}
		return instance;
	}

	public void subscribe(EventType eventType, EventListener listener) 
	{ 
		if(!subscribers.containsKey(eventType))
		{
			subscribers.put(eventType, new ArrayList<EventListener>());
		}
		subscribers.get(eventType).add(listener);
	}

	public <T> void publish(Event<T> event) 
	{ 
		if(subscribers.containsKey(event.type()))
		{
			for(EventListener eventListener: subscribers.get(event.type()))
				eventListener.onEvent(event);
		}
	}

  // SINGLETON
}
