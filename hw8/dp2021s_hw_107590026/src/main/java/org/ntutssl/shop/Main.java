package org.ntutssl.shop;

public class Main 
{
	public static void main(String args[]) 
	{ 
		EventManager eventManager = EventManager.getInstance();
		eventManager.publish(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
		InstructionHandler instructionHandler = new InstructionHandler();
		instructionHandler.run();
	}
}
