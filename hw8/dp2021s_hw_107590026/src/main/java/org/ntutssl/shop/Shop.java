package org.ntutssl.shop;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop implements EventListener 
{
	private EventManager eventManager;
	private Map<Goods,Integer> shopMap;

	public Shop() 
	{ 
		eventManager = EventManager.getInstance();
		eventManager.subscribe(EventType.REPLENISH, this);
		eventManager.subscribe(EventType.CHECK_STOCK, this);
		eventManager.subscribe(EventType.PURCHASE, this);
		eventManager.subscribe(EventType.LIST_SHOP, this);
		shopMap = new HashMap<>();
	}  

	public void onEvent(Event event) 
	{ 
		if(event.type() == EventType.REPLENISH)
			replenish(event);
		else if(event.type() == EventType.CHECK_STOCK)
			checkStock(event);
		else if(event.type() == EventType.PURCHASE)
			purchase(event);
		else if(event.type() == EventType.LIST_SHOP)
			listShop();
	}

	/**
	 * replenish stock
	 * @param event Event of Goods to replenish
	 */
	private void replenish(Event<Goods> event) 
	{ 
		if(shopMap.containsKey(event.data()))
		{
			shopMap.replace(event.data(), shopMap.get(event.data()) + event.count());
			return;
		}
		shopMap.put(event.data(), event.count());
	}

	/**
	 * check if the specific goods is in stock, if so, publish an 
	 * event ADD_TO_CART
	 * @param event Event of Goods to check
	 */
	private void checkStock(Event<Goods> event) 
	{ 
		Boolean containGood = false;
		for(Map.Entry<Goods, Integer> entry : shopMap.entrySet())
		{
			if(entry.getKey().name().equals(event.data().name()))
			{
				if(entry.getValue() >= event.count())
					EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART, event.data(), event.count()));
				else
					System.out.print("out of stock. goods ID: " + event.data().id() + "\n");
				containGood = true;
				break;
			}
		}
		if(containGood == false)
		{
			System.out.print("No Such Goods.\n");
		}
	}

	/**
	 * deduct stock after user complete purchase
	 * @param event Event of Goods to be deducted
	 */
	private void purchase(Event<Goods> event) 
	{ 

	}

	/**
	 * show stocks of this shop
	 */
	private void listShop() { }
}
