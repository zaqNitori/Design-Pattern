package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.swing.SortOrder;
import org.junit.Test;

public class WordFrequencyManagerTest 
{ 
    @Test
    public void MapStoreCorrectFrequency()
    {
        WordFrequencyManager wfm = new WordFrequencyManager();
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
        WordFrequencyManager wfm = new WordFrequencyManager();
        wfm.incrementCount("one");
        wfm.incrementCount("two");
        wfm.incrementCount("three");
        assertEquals(Integer.valueOf(3), Integer.valueOf(wfm.getNumOfWords()));
    }

    @Test
    public void MapSortCorrect()
    {
        WordFrequencyManager wfm = new WordFrequencyManager();
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
                    assertEquals("one", s);
                    break;
                case 2:
                    assertEquals("two", s);
                    break;
                case 3:
                    assertEquals("three", s);
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
                    assertEquals("one", s);
                    break;
                case 2:
                    assertEquals("two", s);
                    break;
                case 3:
                    assertEquals("three", s);
                    break;
            }
            test--;
        }
    }
}
