package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements EventListener 
{
	private EventManager eventManager;
	private List<Event<Goods>> goodList;

	public ShoppingCart() 
	{ 
		eventManager = EventManager.getInstance();
		eventManager.subscribe(EventType.ADD_TO_CART, this);
		eventManager.subscribe(EventType.PAY, this);
		eventManager.subscribe(EventType.LIST_CART, this);

		goodList = new ArrayList<>();
	}

	public void onEvent(Event event) 
	{ 
		if(event.type() == EventType.ADD_TO_CART)
			add(event);
		else if(event.type() == EventType.PAY)
			pay();
		else if(event.type() == EventType.LIST_CART)
			listCart();
	}

	/**
	 * private methods are not necessary, but you can takce them as reference.
	 */
	/**
	 * add goods to shopping cart
	 * @param goodsEvent the data of this event is the goods to be added
	 */
	private void add(Event<Goods> event) 
	{ 
		goodList.add(event);
	}

	/**
	 * pay for all items in the shopping cart
	 */
	private void pay() 
	{ 
		double sum = 0;
		for(Event<Goods> event: goodList)
			sum += event.count() * event.data().price();
		System.out.printf("Total Price: $%.2f", sum);
	}

	/**
	 * list all items in the shopping cart
	 */
	private void listCart() 
	{ 
		goodList.forEach((event) -> System.out.printf("%-4s%-22s%-40s%-8s%-6s\n"
			, String.valueOf(event.data().id()), event.data().name(), event.data().description()
			, event.data().price(), event.count()));
	}
}
