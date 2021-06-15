package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RateDiscountDecoratorTest 
{ 

    public Merchandise merchandize = new Merchandise(1, "hi", "hello", 500);
    public Collection collection = new Collection(1, "hico", "helloco");
    public Decorator decorator;

    @Test
    public void testEmptyCollection(){
        
        decorator = new RateDiscountDecorator(collection, 0.1);

        assertEquals(0, decorator.price(), 0.01);
    }

    @Test(expected = ShopException.class)
    public void testEmptyCollectionNotInRange()
    {
        decorator = new RateDiscountDecorator(collection, 1.1);
    }

    @Test
    public void testCollection()
    {
        collection.add(merchandize);
        decorator = new RateDiscountDecorator(collection, 0.1);
        
        assertEquals(50, decorator.price(), 0.01);
    }

    @Test
    public void testMerchandize()
    {
        decorator = new RateDiscountDecorator(merchandize, 0.1);
        assertEquals(50, decorator.price(), 0.01);
    }

    @Test(expected = ShopException.class)
    public void testRateNotInRange()
    {
        decorator = new RateDiscountDecorator(merchandize, 1.1);
    }

    @Test(expected = ShopException.class)
    public void testRateNotInRange2()
    {
        decorator = new RateDiscountDecorator(merchandize, -0.5);
    }

}
