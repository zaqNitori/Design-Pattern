package org.ntutssl.termfrequency;

public class Main {
    public static void main(String[] args)
    { 
        IOHandler ioHandler = new IOHandler();
        IStopWordManager swm = new StopWordManager(args[0]/*"input/stop_words.txt"*/, ioHandler);
        IDataStorageManager dsm = new DataStorageManager(args[1]/*"input/pride-and-prejudice.txt"*/, ioHandler);
        IWordFrequencyManager wfm = new WordFrequencyManagerStream();
        WordFrequencyController wfc = new WordFrequencyController(ioHandler, swm, dsm, wfm);
        wfc.run(args[4]/*order*/, Integer.parseInt(args[3])/*range*/, args[2]/*outputPath*/);
    }
}