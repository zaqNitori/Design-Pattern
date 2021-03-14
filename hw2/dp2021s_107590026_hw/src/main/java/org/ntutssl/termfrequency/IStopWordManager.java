package org.ntutssl.termfrequency;

public interface IStopWordManager 
{
    public boolean isStopWordList(String word);
                    
    public boolean isStopWordSet(String word);
}