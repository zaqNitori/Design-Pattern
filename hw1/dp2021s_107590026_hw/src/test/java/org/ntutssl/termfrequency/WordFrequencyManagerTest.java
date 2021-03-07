package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;


public class WordFrequencyManagerTest{ 
    
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
        Map<String,Integer> mp = new LinkedHashMap<String,Integer>();
        wfm.incrementCount("two");
        wfm.incrementCount("three");
        wfm.incrementCount("three");
        wfm.incrementCount("one");
        wfm.incrementCount("two");
        wfm.incrementCount("three");
        mp = wfm.getWordFrequencyDescending();
        int test=3;
        for (Map.Entry<String, Integer> mapping : mp.entrySet()){
            switch(test)
            {
                case 1:
                    assertEquals("one", mapping.getKey());
                    break;
                case 2:
                    assertEquals("two", mapping.getKey());
                    break;
                case 3:
                    assertEquals("three", mapping.getKey());
                    break;
            }
            assertEquals(Integer.valueOf(test--), mapping.getValue());
        }
        mp.clear();
        test=1;
        mp = wfm.getWordFrequencyAscending();
        for (Map.Entry<String, Integer> mapping : mp.entrySet()){
            switch(test)
            {
                case 1:
                    assertEquals("one", mapping.getKey());
                    break;
                case 2:
                    assertEquals("two", mapping.getKey());
                    break;
                case 3:
                    assertEquals("three", mapping.getKey());
                    break;
            }
            assertEquals(Integer.valueOf(test++), mapping.getValue());
        }
    }

}   ////end