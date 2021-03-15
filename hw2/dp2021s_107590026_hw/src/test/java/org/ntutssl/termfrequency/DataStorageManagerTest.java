package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class DataStorageManagerTest 
{ 
    @Test
    public void TestDataStorageManager()
    {
        IOHandler ioHandler = new IOHandler();
        DataStorageManager dsm = new DataStorageManager("input/pride-and-prejudice.txt",ioHandler);
        List<String> wordsList = dsm.getWords();
        assertTrue(wordsList.get(0).equals("the"));
        assertTrue(wordsList.contains("elizabeth"));
    }
}
