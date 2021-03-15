package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;


public class IOHandlerTest 
{ 
    @Test
    public void testCorrectInput()
    {
        IOHandler ioHandler = new IOHandler();
        String pattern = ",", path = "input/stop_words.txt";
        List<String> list = ioHandler.handleInputAsList(path,pattern);
        Set<String> set = ioHandler.handleInputAsSet(path, pattern);

        assertTrue(list.contains("a"));
        assertTrue(list.contains("your"));
        assertTrue(set.contains("a"));
        assertTrue(set.contains("your"));
    }

    @Test
    public void testPath()
    {
        IOHandler ioHandler = new IOHandler();
        String pattern = ",";
        String correctPath = "input/stop_words.txt", wrongPath = "nothing/here.txt";
        Set<String> setWrong = ioHandler.handleInputAsSet(wrongPath, pattern);
        assertFalse(ioHandler.isFileOpen());
        assertEquals(Integer.valueOf(0), Integer.valueOf(setWrong.size()));
        Set<String> setCorrect = ioHandler.handleInputAsSet(correctPath, pattern);
        assertTrue(ioHandler.isFileOpen());
        assertFalse(setCorrect.size() == 0);
    }

}
