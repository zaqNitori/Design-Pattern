package org.ntutssl.shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayByCreditCardStrategyTest 
{ 
    PayStrategy strategy = new PayByCreditCardStrategy();
    EventManager eventManager = EventManager.getInstance();
    
    @Before
    public void init()
    {
        eventManager.reset();
    }

    @Test
    public void testCalculateShouldBeCorrect()
    {
        strategy.calculate(20);
        //assertEquals("$18.00", strategy);
    }
}
