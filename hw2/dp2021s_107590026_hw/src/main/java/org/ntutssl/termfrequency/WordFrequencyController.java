package org.ntutssl.termfrequency;

public class WordFrequencyController {

    private IOHandler ioHandler;
    private IDataStorageManager idsm;
    private IStopWordManager iswm;
    private IWordFrequencyManager iwfm;

    public WordFrequencyController(
        IOHandler handler, 
        IStopWordManager swm, 
        IDataStorageManager dsm, 
        IWordFrequencyManager wfm
    )
    { 
        ioHandler = handler;
        iwfm = wfm;
        iswm = swm;
        idsm = dsm;
    }

    public void run(String order, Integer range, String outputPath) 
    { 
        int i=0;
        for(String word : idsm.getWords())
        {             
            /*if(i++ > 15) break;
            System.out.println(word);*/
            if(iswm.isStopWordSet(word)==false)
                iwfm.incrementCount(word);       
        }         
        iwfm.output(outputPath, order, range, ioHandler);
    }
}