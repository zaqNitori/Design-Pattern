package org.ntutssl.termfrequency;
import static org.junit.Assert.*;
import org.junit.Test;
public class WordFrequencyManagerTest implements EventListener{
    public int outputNum = 0;
    public void onEvent(EventType eventType, String event) {
        if(eventType == EventType.OUTPUT)
            this.outputNum++;
    }
    @Test
    public void WordFrequencyManagerShouldAcceptDrivenCount(){
            EventManager em = new EventManager();
            WordFrequencyManager wfm = new WordFrequencyManager(em);
            em.publish(EventType.COUNT, "Test");
            em.publish(EventType.COUNT, "File");
            em.publish(EventType.COUNT, "Test");
            assertEquals(wfm.getNumOfWords(), 2);
            assertEquals(wfm.getCount("Test"), Integer.valueOf(2));
            assertEquals(wfm.getCount("File"), Integer.valueOf(1));
    }
    @Test
    public void WordFrequencyManagerShouldAcceptEofAndPublishOutput(){
            this.outputNum = 0;
            EventManager em = new EventManager();
            WordFrequencyManager wfm = new WordFrequencyManager(em);
            em.subscribe(EventType.OUTPUT, this);
            em.publish(EventType.EOF, "Test");
            assertEquals(this.outputNum, 1);
    }
 }
