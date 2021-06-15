package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PayByCreditCardStrategyTest 
{ 
    public PayByCreditCardStrategy strategy = new PayByCreditCardStrategy();
    public EventManager eventManager = EventManager.getInstance();
    
    @Before
    public void init()
    {
        eventManager.reset();
    }

    @Test
    public void testCalculate()
    {

        strategy.calculate(20);
        assertEquals("$18.00",strategy.getCalculate());
    }
}
