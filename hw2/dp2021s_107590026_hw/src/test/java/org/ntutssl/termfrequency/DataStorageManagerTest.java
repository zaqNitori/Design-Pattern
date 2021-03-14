package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class DataStorageManagerTest 
{ 
    @Test
    public void TestDataStorageManager()
    {
        IOHandler ioHandler = new IOHandler();
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt",ioHandler);
        List<String> wordsList = dsm.getWords();
        Set<String> wordsSet = dsm.getSet();
        int i=0;
        for(String s:wordsList)                         //test all case of string words
        {
            if(i++ > 100) break;
            assertTrue(wordsSet.contains(s));
        }
    }
}
