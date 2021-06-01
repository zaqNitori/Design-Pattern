package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.List;

public class Shop implements EventListener 
{
	private EventManager eventManager;
	private List<Event<Goods>> goodList;

	public Shop() 
	{ 
		eventManager = EventManager.getInstance();
		eventManager.subscribe(EventType.REPLENISH, this);
		eventManager.subscribe(EventType.CHECK_STOCK, this);
		eventManager.subscribe(EventType.PURCHASE, this);
		eventManager.subscribe(EventType.LIST_SHOP, this);

		goodList = new ArrayList<>();
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
	 * private methods are not necessary, but you can takce them as reference.
	 */
	/**
	 * replenish stock
	 * @param event Event of Goods to replenish
	 */
	private void replenish(Event<Goods> event) 
	{ 
		goodList.add(event);
	}

	/**
	 * check if the specific goods is in stock, if so, publish an 
	 * event ADD_TO_CART
	 * @param event Event of Goods to check
	 */
	private void checkStock(Event<Goods> event) 
	{ 

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
	private void listShop() 
	{ 
		goodList.forEach((event) -> System.out.printf("%-4s%-22s%-40s%-8s%-6s\n"
			, String.valueOf(event.data().id()), event.data().name(), event.data().description()
			, event.data().price(), event.count()));
	}
}
