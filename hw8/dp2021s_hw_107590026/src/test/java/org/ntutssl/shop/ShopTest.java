package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class ShopTest 
{ 
    public Shop shop;
    public EventManager eventManager = EventManager.getInstance();
    public Merchandise merchandise = new Merchandise(1, "hi", "hello", 20);
    public EventListener listener;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = System.out;

    @Before
    public void init()
    {
        eventManager.reset();
        shop = new Shop();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    public void testPurcaseWithExistGoods()
    {
        listener = new TestListener(EventType.ADD_TO_CART);

        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 50));
        shop.onEvent(new GoodsEvent(EventType.PURCHASE, merchandise, 5));
    }

    @Test
    public void testPurcaseWithExistGoodsButNoStocks()
    {

        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 2));
        shop.onEvent(new GoodsEvent(EventType.PURCHASE, merchandise, 5));
        assertEquals("Out of Stock. goods ID: 1\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testPurcaseWithNonExistGoods()
    {
        shop.onEvent(new GoodsEvent(EventType.PURCHASE, merchandise, 5));
        assertEquals("No Such Goods.\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testCheckStockWithExistGoods()
    {
        listener = new TestListener(EventType.ADD_TO_CART);
        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 50));
        shop.onEvent(new GoodsEvent(EventType.CHECK_STOCK, merchandise, 10));

        assertTrue(((TestListener)listener).getTrigger());
    }

    @Test
    public void testCheckStockWithExistGoodsButNoStocks()
    {
        listener = new TestListener(EventType.ADD_TO_CART);
        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 10));
        shop.onEvent(new GoodsEvent(EventType.CHECK_STOCK, merchandise, 20));

        assertEquals("Out of Stock. goods ID: 1\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testCheckStockWithNonExistGoods()
    {
        listener = new TestListener(EventType.ADD_TO_CART);
        shop.onEvent(new GoodsEvent(EventType.CHECK_STOCK, merchandise, 10));

        assertEquals("No Such Goods.\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testListShopEmpty()
    {
        eventManager.publish(new StringEvent(EventType.LIST_SHOP, ""));
        
        assertEquals("Sell Nothing\n", byteArrayOutputStream.toString());
    }

    private class TestListener implements EventListener
    {
        EventManager eventManager = EventManager.getInstance();
        public boolean onTrigger = false;
        public TestListener(EventType type)
        {
            eventManager.subscribe(type, this);
        }
        public void onEvent(Event event)
        {
            onTrigger = true;
        }
        public boolean getTrigger()
        {
            return onTrigger;
        }
    }
}
