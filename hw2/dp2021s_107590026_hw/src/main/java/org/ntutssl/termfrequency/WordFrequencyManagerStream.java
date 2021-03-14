package org.ntutssl.termfrequency;

import javax.swing.SortOrder;
import java.util.List;

public class WordFrequencyManagerStream implements IWordFrequencyManager {

    public WordFrequencyManagerStream() { }

    public void incrementCount(String word) { }

    public int getNumOfWords() { return 0; }

    public List<String> getWordFrequency(SortOrder order) { return null; }

    public void output(
        String outputPath,
        String order,
        int range,
        IOHandler handler
    )
    { 
        handler.handleOutput(outputPath, range, null);
    }
}