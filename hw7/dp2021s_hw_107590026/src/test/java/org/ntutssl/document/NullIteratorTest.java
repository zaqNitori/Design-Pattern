package org.ntutssl.document;

import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NullIteratorTest 
{

    public NullIterator nullIterator;
    @Rule
    public ExpectedException expect = ExpectedException.none();

    @Before
    public void init()
    {
        nullIterator = new NullIterator();
    }

    @Test
    public void testHasNext()
    {
        assertFalse(nullIterator.hasNext());
    }

    @Test
    public void testNext()
    {
        expect.expect(NoSuchElementException.class);
        expect.expectMessage("No Such Element!");

        nullIterator.next();
    }

}