package org.ntutssl.shop;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart implements EventListener 
{

	private EventManager eventManager;
	private Map<Goods,Integer> cartMap;

	public ShoppingCart() 
	{ 
		eventManager = EventManager.getInstance();
		eventManager.subscribe(EventType.ADD_TO_CART, this);
		eventManager.subscribe(EventType.PAY, this);
		eventManager.subscribe(EventType.LIST_CART, this);
		eventManager.subscribe(EventType.PRINT_RECEIPT, this);
		cartMap = new HashMap<>();
	}

	public void onEvent(Event event) 
	{ 
		if(event.type() == EventType.ADD_TO_CART)
			add(event);
		else if(event.type() == EventType.PAY)
			pay();
		else if(event.type() == EventType.LIST_CART)
			listCart();
		else if(event.type() == EventType.PRINT_RECEIPT)
			printReceipt(event);
	}

	/**
	 * add goods to shopping cart
	 * @param goodsEvent the data of this event is the goods to be added
	 */
	private void add(Event<Goods> event) 
	{ 
		if(cartMap.containsKey(event.data()))
			cartMap.replace(event.data(), cartMap.get(event.data()) + 1);
		else
			cartMap.put(event.data(), event.count());
	}

	/**
	 * pay for all items in the shopping cart
	 */
	private void pay() 
	{ 
		double sum = 0;
		for(Map.Entry<Goods, Integer> entry : cartMap.entrySet())
			sum += entry.getKey().price() * entry.getValue();
		System.out.printf("Total Price: $%.2f", sum);
    	eventManager.publish(new StringEvent(EventType.CALCULATE, String.valueOf(sum)));
	}

	/**
	 * print receipt and publish PURCHASE
	 */
	private void printReceipt(Event<String> event) 
	{ 
		System.out.print("================================================================================\n");
		System.out.print("Receipt:\n");
		System.out.printf("%-40s%-10s%-10s\n","name","price","count");

		for(Map.Entry<Goods, Integer> entry : cartMap.entrySet())
		{
			eventManager.publish(new GoodsEvent(EventType.PURCHASE, entry.getKey(), entry.getValue()));

			System.out.printf("%-40s%-10s%-10s\n"
			, entry.getKey().name(), "$" + String.valueOf(entry.getKey().price())
			, String.valueOf(entry.getValue()));
		}

		System.out.print("--------------------------------------------------------------------------------\n");
		System.out.print("Total Price: " + event.data() + "\n");
		System.out.print("================================================================================\n");  
	
		cartMap.clear();
	}

	/**
	 * list all items in the shopping cart
	 */
	private void listCart() 
	{ 
		if(cartMap.isEmpty())
		{
			System.out.print("Nothing in ShoppingCart.\n");
			return;
		}
		System.out.print("================================================================================\n");
		System.out.printf("%-4s%-22s%-40s%-8s%-6s\n", "ID", "name", "description", "price", "count");
		System.out.print("--------------------------------------------------------------------------------\n");
		for(Map.Entry<Goods, Integer> entry : cartMap.entrySet())
		{
			System.out.printf("%-4s%-22s%-40s%-8s%-6s\n"
			, String.valueOf(entry.getKey().id())
			, entry.getKey().name()
			, entry.getKey().description()
			, entry.getKey().price()
			, entry.getValue());
		}
		System.out.print("================================================================================\n");
	}
}
