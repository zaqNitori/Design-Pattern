package org.ntutssl.termfrequency;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class StopWordManagerTest{ 

    @Test
    public void GetCorrectStopWords()
    {
        StopWordManager swm = new StopWordManager("input/stop_words.txt");
        assertTrue(swm.isStopWordList("a"));                 //test head
        assertTrue(swm.isStopWordList("your"));              //test tail
        assertFalse(swm.isStopWordList("elizabeth"));        //test null
        assertTrue(swm.isStopWordSet("a"));
        assertTrue(swm.isStopWordSet("your"));
        assertFalse(swm.isStopWordSet("elizabeth"));
        for(char c='A'; c <= 'Z'; c++)                              //test alphabet
        {
            assertTrue(swm.isStopWordList(String.valueOf(c)));
            assertTrue(swm.isStopWordList(String.valueOf((char)(c+32))));
            assertTrue(swm.isStopWordSet(String.valueOf(c)));
            assertTrue(swm.isStopWordSet(String.valueOf((char)(c+32))));
        }
    }

    @Test
    public void CompareSpeedWithTwoStructure()
    {
        StopWordManager swm = new StopWordManager("input/stop_words.txt");
        long listTime,setTime;
        long startTime,endTime;
        startTime = System.nanoTime();                              //get list start searching time
        for(char c='A'; c <= 'Z'; c++)                              //test alphabet
        {
            assertTrue(swm.isStopWordList(String.valueOf(c)));
            assertTrue(swm.isStopWordList(String.valueOf((char)(c+32))));
        }
        endTime = System.nanoTime();                                //get list end searching time
        listTime =endTime - startTime;                              //the real time list spend
        startTime = System.nanoTime();                              //get set start searching time
        for(char c='A'; c <= 'Z'; c++)                              //test alphabet
        {
            assertTrue(swm.isStopWordSet(String.valueOf(c)));
            assertTrue(swm.isStopWordSet(String.valueOf((char)(c+32))));
        }
        endTime = System.nanoTime();                                //get set end searching time
        setTime = endTime - startTime;                              //the real time set spend
        System.out.println("time list spend to search all alphabet => " + listTime + " ns");
        System.out.println("time set spend to search all alphabet => " + setTime + " ns");
        assertTrue(setTime < listTime);                             //test that set speed faster than list
    }
}