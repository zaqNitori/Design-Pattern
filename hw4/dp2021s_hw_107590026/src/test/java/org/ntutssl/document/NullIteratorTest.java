package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class NullIteratorTest 
{ 
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void testnullIterHasNext()
    {
        NullIterator nullIterator = new NullIterator();

        assertFalse(nullIterator.hasNext());

    }

    @Test
    public void testnullIterNext()
    {
        NullIterator nullIterator = new NullIterator();

        expected.expect(NoSuchElementException.class);
        expected.expectMessage("No Such Element!");
        nullIterator.next();
    }

}