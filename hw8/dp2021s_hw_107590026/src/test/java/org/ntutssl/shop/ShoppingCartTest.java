package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ShoppingCartTest 
{ 
    public ShoppingCart cart;
    public EventManager eventManager = EventManager.getInstance();
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = System.out;
    public Collection collection = new Collection(1, "empty", "empty");

    @Before
    public void init()
    {
        eventManager.reset();
        cart = new ShoppingCart();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    public void testAddEmptyCollection()
    {
        eventManager.publish(new GoodsEvent(EventType.ADD_TO_CART, collection, 10));
        eventManager.publish(new StringEvent(EventType.LIST_CART, ""));

        assertEquals(
          "================================================================================\n"
        + "ID  name                  description                             price   count \n"
        + "--------------------------------------------------------------------------------\n"
        + String.format("%-4s%-22s%-40s%-8s%-6s\n", 1,"empty","empty",0.0,10)
        + "================================================================================\n"
        ,byteArrayOutputStream.toString());
    }

    @Test
    public void testAddCollectionWithMerchandize()
    {
        collection.add(new Merchandise(2, "m", "mm", 5));
        eventManager.publish(new GoodsEvent(EventType.ADD_TO_CART, collection, 10));
        eventManager.publish(new StringEvent(EventType.LIST_CART, ""));

        assertEquals(
          "================================================================================\n"
        + "ID  name                  description                             price   count \n"
        + "--------------------------------------------------------------------------------\n"
        + String.format("%-4s%-22s%-40s%-8s%-6s\n", 1,"empty","empty",5.0,10)
        + "================================================================================\n"
        ,byteArrayOutputStream.toString());
    }

    @Test
    public void testAddCollectionWithMerchandizeAndCollection()
    {
        collection.add(new Merchandise(2, "m", "m", 5));
        Collection collection2 = new Collection(3, "empty2", "empty2");
        collection2.add(new Merchandise(4, "m2", "m2", 10));
        collection.add(collection2);
        
        eventManager.publish(new GoodsEvent(EventType.ADD_TO_CART, collection, 10));
        eventManager.publish(new StringEvent(EventType.LIST_CART, ""));
        assertEquals(
          "================================================================================\n"
        + "ID  name                  description                             price   count \n"
        + "--------------------------------------------------------------------------------\n"
        + String.format("%-4s%-22s%-40s%-8s%-6s\n", 1,"empty","empty",15.0,10)
        + "================================================================================\n"
        ,byteArrayOutputStream.toString());
    }

    @Test
    public void testMoreThanOneGoods()
    {
        eventManager.publish(new GoodsEvent(EventType.ADD_TO_CART, collection, 10));
        eventManager.publish(new GoodsEvent(EventType.ADD_TO_CART, new Merchandise(2, "m", "m", 20), 30));
        eventManager.publish(new StringEvent(EventType.LIST_CART, ""));

        assertEquals(
          "================================================================================\n"
        + "ID  name                  description                             price   count \n"
        + "--------------------------------------------------------------------------------\n"
        + String.format("%-4s%-22s%-40s%-8s%-6s\n", 1,"empty","empty",0.0,10)
        + String.format("%-4s%-22s%-40s%-8s%-6s\n", 2,"m","m",20.0,30)
        + "================================================================================\n"
        ,byteArrayOutputStream.toString());
    }

    @Test
    public void testListCart()
    {
        eventManager.publish(new StringEvent(EventType.LIST_CART, ""));
        assertEquals("Your shopping cart is empty\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testPayTriggerPublishCalculate()
    {
        eventManager.publish(new StringEvent(EventType.PAY, "123"));
    }

    @Test
    public void testPrintRecieptTriggerPublishPhurchase()
    {
        eventManager.publish(new GoodsEvent(EventType.ADD_TO_CART, collection, 10));
        eventManager.publish(new StringEvent(EventType.PRINT_RECEIPT, ""));
    }

    @After
    public void end()
    {
        System.setOut(printStream);
    }
}
