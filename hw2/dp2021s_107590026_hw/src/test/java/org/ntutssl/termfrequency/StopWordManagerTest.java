package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class StopWordManagerTest 
{ 
    @Test
    public void GetCorrectStopWords()
    {
        IOHandler ioHandler = new IOHandler();
        IStopWordManager swm = new StopWordManager("input/stop_words.txt",ioHandler);
        assertTrue(swm.isStopWordList("a"));                 //test head
        assertTrue(swm.isStopWordList("your"));              //test tail
        assertFalse(swm.isStopWordList("elizabeth"));        //test null
        assertTrue(swm.isStopWordSet("a"));
        assertTrue(swm.isStopWordSet("your"));
        assertFalse(swm.isStopWordSet("elizabeth"));
        for(char c='A'; c <= 'Z'; c++)                              //test alphabet
        {
            assertTrue(swm.isStopWordSet(String.valueOf(c)));
            assertTrue(swm.isStopWordSet(String.valueOf((char)(c+32))));
            assertTrue(swm.isStopWordList(String.valueOf(c)));
            assertTrue(swm.isStopWordList(String.valueOf((char)(c+32))));
        }
    }

}
