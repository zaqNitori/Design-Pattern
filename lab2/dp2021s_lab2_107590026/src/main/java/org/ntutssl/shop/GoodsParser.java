package org.ntutssl.shop;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class GoodsParser implements EventListener 
{
	private EventManager eventManager;
	private GoodsBuilder builder;
	private int count;

	public GoodsParser() 
	{ 
		builder = new GoodsBuilder();
		eventManager = EventManager.getInstance();
		eventManager.subscribe(EventType.IMPORT_REPLENISH_LIST, this);
		eventManager.subscribe(EventType.IMPORT_SHOPPING_LIST, this);
	}

	public void onEvent(Event event) 
	{ 
		if(event.type() == EventType.IMPORT_REPLENISH_LIST)
		{
			importReplenishmentList(event);
		}
		else if(event.type() == EventType.IMPORT_SHOPPING_LIST)
		{
			importShoppingCartList(event);
		}
	}

	/**
	 * private methods are not necessary, but you can takce them as reference.
	 */
	private void importShoppingCartList(Event<String> event) 
	{ 
		try (JsonReader reader = new JsonReader(new FileReader(event.data())))
		{
			JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

			jsonArray.forEach((obj) -> eventManager.publish(
				new GoodsEvent(EventType.IMPORT_SHOPPING_LIST, parse(obj.getAsJsonObject()), count)));
		} 
		catch (IOException e) 
		{
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	private void importReplenishmentList(Event<String> event) 
	{ 
		try (JsonReader reader = new JsonReader(new FileReader(event.data())))
		{
			JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

			jsonArray.forEach((obj) -> eventManager.publish(
				new GoodsEvent(EventType.IMPORT_REPLENISH_LIST, parse(obj.getAsJsonObject()), count)));	
		} 
		catch (IOException e) 
		{
			//TODO: handle exception
			e.printStackTrace();
		}
	}

	private Goods parse(JsonObject jsonObj) 
	{ 
		build(jsonObj);
		return builder.getResult();
	}

	private void build(JsonObject jsonObject)
	{
		int id;
		String name;
		String desc;
		double price;

		id = Integer.valueOf(jsonObject.get("id").getAsString());
		name = jsonObject.get("name").getAsString();
		desc = jsonObject.get("description").getAsString();
		if(jsonObject.get("count") != null)
			Integer.valueOf(jsonObject.get("count").getAsString());

		switch (jsonObject.get("type").getAsString()) 
		{
			case "merchandise":
				price = Double.parseDouble(jsonObject.get("price").getAsString());
				builder.buildMerchandise(id, name, desc, price);
			
				break;
			case "collection":
				builder.startBuildCollection(id, name, desc);
				if(jsonObject.get("contents") != null)
					jsonObject.get("contents").getAsJsonArray().forEach((obj) -> build(obj.getAsJsonObject()));
				builder.endBuildCollection();
				break;
		}
	}

}
