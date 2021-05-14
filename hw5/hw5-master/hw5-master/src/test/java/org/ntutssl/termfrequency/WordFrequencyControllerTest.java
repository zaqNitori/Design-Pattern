package org.ntutssl.termfrequency;
import static org.junit.Assert.*;
import org.junit.Test;
public class WordFrequencyControllerTest implements EventListener{
    public int loadNum = 0;
    public int runNum = 0;
    public void onEvent(EventType eventType, String event) {
        if(eventType == EventType.LOAD)
            this.loadNum++;
        if(eventType == EventType.RUN)
            this.runNum++;
    }

    @Test
    public void WordFrequencyControllerShouldAcceptStartAndPublishLoadAndRun(){
            EventManager em = new EventManager();
            WordFrequencyController wfm = new WordFrequencyController(em);
            em.subscribe(EventType.LOAD, this);
            em.subscribe(EventType.RUN, this);
            em.publish(EventType.START, "input/test.txt");
            assertEquals(loadNum, 1);
            assertEquals(runNum, 1);
    }
 }
