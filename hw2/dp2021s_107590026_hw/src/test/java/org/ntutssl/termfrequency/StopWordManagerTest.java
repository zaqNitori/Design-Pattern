package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import com.google.common.base.Stopwatch;

public class StopWordManagerTest 
{ 
    @Test
    public void GetCorrectStopWords()
    {
        IOHandler ioHandler = new IOHandler();
        IStopWordManager swm = new StopWordManager("input/stop_words.txt",ioHandler);
        assertTrue(swm.isStopWordList("a"));                    //test head
        assertTrue(swm.isStopWordList("your"));                 //test tail
        assertFalse(swm.isStopWordList("elizabeth"));           //test null
        assertTrue(swm.isStopWordSet("a"));
        assertTrue(swm.isStopWordSet("your"));
        assertFalse(swm.isStopWordSet("elizabeth"));
        for(char c='A'; c <= 'Z'; c++)                          //test alphabet
        {
            assertTrue(swm.isStopWordSet(String.valueOf(c)));
            assertTrue(swm.isStopWordSet(String.valueOf((char)(c+32))));
            assertTrue(swm.isStopWordList(String.valueOf(c)));
            assertTrue(swm.isStopWordList(String.valueOf((char)(c+32))));
        }
    }

    @Test
    public void CompareSpeedWithTwoStructure()
    {
        IOHandler ioHandler = new IOHandler();
        IStopWordManager swm = new StopWordManager("input/stop_words.txt",ioHandler);
        long listTime,setTime;
        Stopwatch sw = Stopwatch.createStarted();
        for(char c='A'; c <= 'Z'; c++)                              //test alphabet
        {
            assertTrue(swm.isStopWordList(String.valueOf(c)));
            assertTrue(swm.isStopWordList(String.valueOf((char)(c+32))));
        }
        sw.stop();
        listTime = sw.elapsed(TimeUnit.NANOSECONDS);
        sw.reset().start();                                         //get set start searching time
        for(char c='A'; c <= 'Z'; c++)                              //test alphabet
        {
            assertTrue(swm.isStopWordSet(String.valueOf(c)));
            assertTrue(swm.isStopWordSet(String.valueOf((char)(c+32))));
        }
        sw.stop();
        setTime = sw.elapsed(TimeUnit.NANOSECONDS);
        System.out.println("time list spend to search all alphabet => " + listTime + " ns");
        System.out.println("time set spend to search all alphabet => " + setTime + " ns");
        assertTrue(setTime < listTime);                             //test that set speed faster than list
    }


}
