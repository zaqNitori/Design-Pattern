package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NullIteratorTest 
{ 
    @Rule
    public ExpectedException expection = ExpectedException.none();

    public NullIterator nullIterator = new NullIterator();

    @Test
    public void testHasNext()
    {
        assertFalse(nullIterator.hasNext());
    }

    @Test
    public void testNext()
    {
        expection.expect(NoSuchElementException.class);
        expection.expectMessage("No Such Element!");
        nullIterator.next();
    }

}