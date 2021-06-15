package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
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
        assertFalse(shop.isTrigger());
        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 50));
        shop.onEvent(new GoodsEvent(EventType.PURCHASE, merchandise, 5));
        assertTrue(shop.isTrigger());
    }

    @Test
    public void testPurcaseWithExistGoodsButNoStocks()
    {

        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 2));
        shop.onEvent(new GoodsEvent(EventType.PURCHASE, merchandise, 5));
        assertEquals("out of stock. goods ID: 1\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testPurcaseWithNonExistGoods()
    {
        shop.onEvent(new GoodsEvent(EventType.PURCHASE, merchandise, 5));
        assertEquals("The store doesn't have this goods.\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testCheckStockWithExistGoods()
    {
        assertFalse(shop.isTrigger());
        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 50));
        shop.onEvent(new GoodsEvent(EventType.CHECK_STOCK, merchandise, 10));
        assertTrue(shop.isTrigger());
    }

    @Test
    public void testCheckStockWithExistGoodsButNoStocks()
    {
        eventManager.publish(new GoodsEvent(EventType.REPLENISH, merchandise, 10));
        shop.onEvent(new GoodsEvent(EventType.CHECK_STOCK, merchandise, 20));

        assertEquals("out of stock. goods ID: 1\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testCheckStockWithNonExistGoods()
    {
        shop.onEvent(new GoodsEvent(EventType.CHECK_STOCK, merchandise, 10));

        assertEquals("The store doesn't have this goods.\n", byteArrayOutputStream.toString());
    }

    @Test
    public void testListShopEmpty()
    {
        eventManager.publish(new StringEvent(EventType.LIST_SHOP, ""));
        
        assertEquals("This shop does not sell anything.\n", byteArrayOutputStream.toString());
    }

    @After
    public void end()
    {
        System.setOut(printStream);
    }

}
