package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class NullIteratorTest 
{ 
    
    public NullIterator nullIterator = new NullIterator();

    @Test
    public void testHasNext()
    {
        assertFalse(nullIterator.hasNext());
    }

    @Test
    public void testNext()
    {
        assertEquals(null, nullIterator.next());
    }

}