package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class IOHandlerTest 
{ 

    @Rule
    public ExpectedException expected = ExpectedException.none();

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
        
        expected.expect(WordFrequencyException.class);
        expected.expectMessage("File not found.");
        Set<String> setWrong = ioHandler.handleInputAsSet(wrongPath, pattern);  

        assertEquals(Integer.valueOf(0), Integer.valueOf(setWrong.size()));
        Set<String> setCorrect = ioHandler.handleInputAsSet(correctPath, pattern);
        assertFalse(setCorrect.size() == 0);
    }

    @Test
    public void testWrite()
    {
        IOHandler ioHandler = new IOHandler();
        List<String> li = new ArrayList<>(), tmp;
        li.add("hi,");
        li.add("bye");
        ioHandler.handleOutput("output/test.txt", 2, li);
        String pattern = ",";
        String path = "output/test.txt";
        tmp = ioHandler.handleInputAsList(path, pattern);
        assertEquals(tmp.get(0), "hi");
        assertEquals(tmp.get(1), "bye");
        File file = new File(path);
        file.delete();
    }

}
