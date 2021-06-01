package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class GoodsBuilderTest 
{ 
    public GoodsBuilder goodsBuilder = new GoodsBuilder();
    public Goods goods;

    public int id = 1;
    public String name = "Hello";
    public String desc = "H123";
    public double price = 12.3;


    @Test
    public void testBuildMerchandise()
    {
        goodsBuilder.buildMerchandise(id, name, desc, price);
        goods = goodsBuilder.getResult();

        assertEquals(id, goods.id());
        assertEquals(name, goods.name());
        assertEquals(desc, goods.description());
        assertEquals(price, goods.price(), 0.01);
    }

    @Test
    public void testBuildCollectionOnly()
    {
        goodsBuilder.startBuildCollection(id, name, desc);
        goodsBuilder.endBuildCollection();
        goods = goodsBuilder.getResult();
        
        assertEquals(id, goods.id());
        assertEquals(name, goods.name());
        assertEquals(desc, goods.description());
    }

    @Test
    public void testBuildCollectionWithMerchandise()
    {
        goodsBuilder.startBuildCollection(id, name, desc);
        goodsBuilder.buildMerchandise(id, name, desc, price);
        goodsBuilder.endBuildCollection();
        goods = goodsBuilder.getResult();
        
        assertEquals(id, goods.id());
        assertEquals(name, goods.name());
        assertEquals(desc, goods.description());
        
        Iterator<Goods> goodIter = goods.iterator();
        assertTrue(goodIter.hasNext());
        Goods good = goodIter.next();

        assertEquals(id, good.id());
        assertEquals(name, good.name());
        assertEquals(desc, good.description());
        assertEquals(price, good.price(), 0.01);
    }

}