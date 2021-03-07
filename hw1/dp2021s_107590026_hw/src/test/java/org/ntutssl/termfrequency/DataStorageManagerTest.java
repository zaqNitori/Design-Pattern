package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class DataStorageManagerTest{

    @Test
    public void TestDataStorageManager()
    {
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt");
        List<String> wordsList = dsm.getWords();
        Set<String> wordsSet = dsm.getSet();
        for(String s:wordsList)                         //test all case of string words
        {
            //System.out.println(s);
            assertTrue(wordsSet.contains(s));
        }
    }


}