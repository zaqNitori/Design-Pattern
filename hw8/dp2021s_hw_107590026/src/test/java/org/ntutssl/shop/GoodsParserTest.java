package org.ntutssl.shop;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GoodsParserTest 
{ 

    GoodsParser parser;
    EventManager eventManager = EventManager.getInstance();
    
    @Before
    public void init()
    {
        parser = new GoodsParser();
        eventManager.reset();
    }

    @Test
    public void testImportReplenishTriggerPublishReplenish()
    {
        assertFalse(parser.isTrigger());
        parser.onEvent(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
        assertTrue(parser.isTrigger());
    }

    @Test
    public void testImportShoppingListTriggerPublishCheckStock()
    {
        assertFalse(parser.isTrigger());
        parser.onEvent(new StringEvent(EventType.IMPORT_SHOPPING_LIST, "input/shopping_list.json"));
        assertTrue(parser.isTrigger());
    }

}
