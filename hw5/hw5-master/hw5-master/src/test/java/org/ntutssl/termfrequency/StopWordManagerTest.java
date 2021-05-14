package org.ntutssl.termfrequency;
import static org.junit.Assert.*;
import org.junit.Test;
public class StopWordManagerTest implements EventListener{
    public int countNum = 0;
    public void onEvent(EventType eventType, String event) {
        if(eventType == EventType.COUNT)
            this.countNum++;
    }
    
    @Test
    public void StopWordManagerShouldAcceptLoadFileWithEvent(){
            EventManager em = new EventManager();
            StopWordManager swm = new StopWordManager(em);
            em.publish(EventType.LOAD, "Test");
            assertEquals(swm.getStopWords().get(0),"a");
            assertTrue(swm.getStopWords().contains("your"));
            assertTrue(swm.getStopWords().contains("z"));
    }

    @Test
    public void StopWordManagerShouldAcceptValidateAndPublishCount(){
            this.countNum = 0;
            EventManager em = new EventManager();
            StopWordManager swm = new StopWordManager(em);
            em.publish(EventType.LOAD, "Test");
            em.subscribe(EventType.COUNT, this);
            em.publish(EventType.VALIDATE, "Test");
            em.publish(EventType.VALIDATE, "File");
            em.publish(EventType.VALIDATE, "your");
            assertEquals(this.countNum,2);
    }
 }
