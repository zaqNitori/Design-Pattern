package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class EventTest 
{ 

    @Test
    public void testType()
    {
        Event stringEvent = new StringEvent(EventType.ADD_TO_CART, "123");

        assertEquals("123", stringEvent.data());
        assertEquals(EventType.ADD_TO_CART, stringEvent.type());
    }

    @Test(expected = ShopException.class)
    public void testStringEventCount()
    {
        Event stringEvent = new StringEvent(EventType.ADD_TO_CART, "123");

        assertEquals("123", stringEvent.data());
        assertEquals(EventType.ADD_TO_CART, stringEvent.type());
        stringEvent.count();
    }

    @Test
    public void testGoodsEventData()
    {
        Goods merchendize = new Merchandise(1, "123", "456", 1);
        Event goodEvent = new GoodsEvent(EventType.ADD_TO_CART, merchendize, 1);

        assertEquals(merchendize, goodEvent.data());
        assertEquals(EventType.ADD_TO_CART, goodEvent.type());
        
    }

    @Test
    public void testStringEventData()
    {
        Event stringEvent = new StringEvent(EventType.ADD_TO_CART, "123");

        assertEquals("123", stringEvent.data());
    }

}
