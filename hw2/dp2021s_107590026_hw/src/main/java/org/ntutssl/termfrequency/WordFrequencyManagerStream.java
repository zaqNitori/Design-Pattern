package org.ntutssl.termfrequency;

import javax.swing.SortOrder;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyManagerStream implements IWordFrequencyManager {

    private Map<String,Integer> _WordFreqMap;

    public WordFrequencyManagerStream() 
    { 
        _WordFreqMap = new HashMap<>();
    }

    public void incrementCount(String word) 
    { 
        word = word.toLowerCase();
        if(_WordFreqMap.containsKey(word))
            _WordFreqMap.put(word,_WordFreqMap.get(word)+1);
        else
            _WordFreqMap.put(word,1);
    }

    public int getNumOfWords() { return _WordFreqMap.size(); }

    public Integer getCount(String word){ 
        if(_WordFreqMap.containsKey(word))
            return _WordFreqMap.get(word);
        else
            return 0;
    }

    public List<String> getWordFrequency(SortOrder order) 
    { 
        List<String> li = _WordFreqMap.entrySet().stream()
        .sorted(Comparator.comparing(Map.Entry::getValue))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
        if(order.equals(SortOrder.DESCENDING)) Collections.reverse(li);
        return li; 
    }

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