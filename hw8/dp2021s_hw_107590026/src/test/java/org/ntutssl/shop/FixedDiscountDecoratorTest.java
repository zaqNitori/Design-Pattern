package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedDiscountDecoratorTest 
{ 

    public Merchandise merchandize = new Merchandise(1, "hi", "hello", 500);
    public Collection collection = new Collection(1, "hico", "helloco");
    public Decorator decorator;

    @Test
    public void testEmptyCollection()
    {
        decorator = new FixedDiscountDecorator(collection, 0);
        
        assertEquals(0, decorator.price(), 0.01);
    }

    @Test(expected = ShopException.class)
    public void testCollectionNotInRange()
    {
        decorator = new FixedDiscountDecorator(collection, 10);
    }

    @Test(expected = ShopException.class)
    public void testCollectionNotInRange2()
    {
        collection.add(merchandize);
        decorator = new FixedDiscountDecorator(collection, 520);
    }

    @Test
    public void testCollection()
    {
        collection.add(merchandize);
        decorator = new FixedDiscountDecorator(collection, 20);
        
        assertEquals(480, decorator.price(), 0.01);
    }

    @Test
    public void testMerchandize()
    {
        decorator = new FixedDiscountDecorator(merchandize, 20);
        assertEquals(480, decorator.price(), 0.01);
    }

    @Test(expected = ShopException.class)
    public void testMerchandizeNotInRange()
    {
        decorator = new FixedDiscountDecorator(merchandize, 520);
    }

}
