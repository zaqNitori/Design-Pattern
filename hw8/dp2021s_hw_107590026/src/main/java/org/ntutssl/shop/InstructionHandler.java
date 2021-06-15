package org.ntutssl.shop;

import java.util.Scanner;

public class InstructionHandler 
{

	private EventManager eventManager;
	private Scanner sc;
	private Boolean exit = false;

	public InstructionHandler() 
	{ 
		eventManager = EventManager.getInstance();
		sc = new Scanner(System.in);
	}

	public void run() 
	{ 
		while(!exit)
		{
			printInstructions();
			handleInstruction(sc.nextLine().trim());
		}
	}

	private void printInstructions() 
	{ 
		System.out.println("Please enter the following instruction(number) to start shopping:");
		System.out.println("1. to list all the goods that the shop has");
		System.out.println("2. to import your shopping list into the shopping cart");
		System.out.println("3. to check the content of your shopping cart");
		System.out.println("4. to go to pay");
		System.out.println("5. to exit the program");
	}

	private void handleInstruction(String instruction) 
	{ 
		switch(instruction)
		{
			case "1":
				instructionListShop();
				break;
			case "2":
				instructionImportShoppingList();
				break;
			case "3":
				instructionListShoppingCart();
				break;
			case "4":
				instructionPay();
				break;
			case "5":
				exit = true;
				break;
			default:
				System.out.println("invalid instruction");
				break;
		  	}
		return;
	}

	private void instructionListShop() 
	{ 
		eventManager.publish(new StringEvent(EventType.LIST_SHOP, ""));
	}

	private void instructionImportShoppingList() 
	{ 
		System.out.print("Please input the file path: ");
		String path = sc.nextLine().trim();
		eventManager.publish(new StringEvent(EventType.IMPORT_SHOPPING_LIST, path));
	}

	private void instructionListShoppingCart() 
	{ 
		eventManager.publish(new StringEvent(EventType.LIST_CART, ""));
	}

	private void instructionPay() 
	{ 
		System.out.println("Please, select a payment method by number:\n1. Bank Transfer\n2. Credit Card\n");
    	String pm = sc.next();
		PayStrategy pay;
		switch(pm)
		{
			case "1":
				pay = new PayByBankTransferStrategy();
				eventManager.publish(new StringEvent(EventType.PAY, ""));
				break;
			case "2":
				pay = new PayByCreditCardStrategy();
				eventManager.publish(new StringEvent(EventType.PAY, ""));
				break;
			default:
				System.out.println("Invalid option.");
				break;
		}
	}
}
