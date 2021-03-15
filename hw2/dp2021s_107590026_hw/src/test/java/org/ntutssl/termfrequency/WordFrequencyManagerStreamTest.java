package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.swing.SortOrder;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class WordFrequencyManagerStreamTest 
{ 

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void MapStoreCorrectFrequency()
    {
        WordFrequencyManagerStream wfm = new WordFrequencyManagerStream();
        wfm.incrementCount("one");
        wfm.incrementCount("two");
        wfm.incrementCount("two");
        assertEquals(Integer.valueOf(2), wfm.getCount("two"));
        assertEquals(Integer.valueOf(1), wfm.getCount("one"));
        assertEquals(Integer.valueOf(0), wfm.getCount("zero"));
    }

    @Test
    public void MapGetCorrectNumOfWord()
    {
        WordFrequencyManagerStream wfm = new WordFrequencyManagerStream();
        wfm.incrementCount("one");
        wfm.incrementCount("two");
        wfm.incrementCount("three");
        assertEquals(Integer.valueOf(3), Integer.valueOf(wfm.getNumOfWords()));
    }

    @Test
    public void MapSortCorrect()
    {
        WordFrequencyManagerStream wfm = new WordFrequencyManagerStream();
        wfm.incrementCount("two");
        wfm.incrementCount("three");
        wfm.incrementCount("three");
        wfm.incrementCount("one");
        wfm.incrementCount("two");
        wfm.incrementCount("three");
        List<String> li = wfm.getWordFrequency(SortOrder.ASCENDING);
        int test=1;
        for (String s: li){
            switch(test)
            {
                case 1:
                    assertEquals("one: 1\n", s);
                    break;
                case 2:
                    assertEquals("two: 2\n", s);
                    break;
                case 3:
                    assertEquals("three: 3\n", s);
                    break;
            }
            test++;
        }
        test = 3;
        li.clear();
        li = wfm.getWordFrequency(SortOrder.DESCENDING);
        for (String s:li){
            switch(test)
            {
                case 1:
                    assertEquals("one: 1\n", s);
                    break;
                case 2:
                    assertEquals("two: 2\n", s);
                    break;
                case 3:
                    assertEquals("three: 3\n", s);
                    break;
            }
            test--;
        }
    }

    @Test
    public void testWordNotFound()
    {
        WordFrequencyManagerStream wfm = new WordFrequencyManagerStream();
        IOHandler ioHandler = new IOHandler();
        
        expected.expect(WordFrequencyException.class);
        expected.expectMessage("Word not found.");
        wfm.output("output/result.txt", "asc", 10, ioHandler);
    }

    @Test
    public void testOrderWrong()
    {
        WordFrequencyManagerStream wfm = new WordFrequencyManagerStream();
        IOHandler ioHandler = new IOHandler();
        
        wfm.incrementCount("abc");
        expected.expect(WordFrequencyException.class);
        expected.expectMessage("The order should be \"asc\" or \"des\".");
        wfm.output("output/result.txt", "abc", 10, ioHandler);
    }

    @Test
    public void testRangeWrong()
    {
        WordFrequencyManagerStream wfm = new WordFrequencyManagerStream();
        IOHandler ioHandler = new IOHandler();
        
        wfm.incrementCount("abc");
        expected.expect(WordFrequencyException.class);
        expected.expectMessage("Out of range! The range should be from 1 to 1.");
        wfm.output("output/result.txt", "asc", 10, ioHandler);
    }


}
