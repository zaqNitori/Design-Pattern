package org.ntutssl.termfrequency;

import javax.swing.SortOrder;
import java.util.List;

public interface IWordFrequencyManager 
{ 
    public void incrementCount(String word);

    public int getNumOfWords();

    public List<String> getWordFrequency(SortOrder order);

    public void output(
        String outputPath,
        String order,
        int range,
        IOHandler handler
    );

}
