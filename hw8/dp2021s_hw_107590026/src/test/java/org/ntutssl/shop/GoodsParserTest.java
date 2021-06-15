package org.ntutssl.shop;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GoodsParserTest 
{ 

    GoodsParser parser;
    EventManager eventManager = EventManager.getInstance();
    EventListener testListener;
    
    @Before
    public void init()
    {
        parser = new GoodsParser();
        eventManager.reset();
    }

    @Test
    public void testImportReplenishTriggerPublishReplenish()
    {
        testListener = new TestListener(EventType.REPLENISH);
        parser.onEvent(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
        assertTrue(((TestListener)testListener).getTrigger());
    }

    @Test
    public void testImportShoppingListTriggerPublishCheckStock()
    {
        testListener = new TestListener(EventType.CHECK_STOCK);
        parser.onEvent(new StringEvent(EventType.IMPORT_SHOPPING_LIST, "input/shopping_list.json"));
        assertTrue(((TestListener)testListener).getTrigger());
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
