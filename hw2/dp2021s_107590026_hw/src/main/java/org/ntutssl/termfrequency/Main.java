package org.ntutssl.termfrequency;

public class Main {
    public static void main(String[] args)
    { 
        IOHandler ioHandler = new IOHandler();
        IDataStorageManager dsm = new DataStorageManager(/*args[1]*/"input/pride-and-prejudice.txt", ioHandler);
        IStopWordManager swm = new StopWordManager(/*args[0]*/"input/stop_words.txt", ioHandler);
        IWordFrequencyManager wfm = new WordFrequencyManagerStream();
        WordFrequencyController wfc = new WordFrequencyController(ioHandler, swm, dsm, wfm);
        
    }
}