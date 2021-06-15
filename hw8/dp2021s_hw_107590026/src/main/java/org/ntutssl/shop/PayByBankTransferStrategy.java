package org.ntutssl.shop;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PayByBankTransferStrategy implements PayStrategy, EventListener 
{

	private EventManager eventManager;
	private Scanner scanner;
	private String ans;

	public PayByBankTransferStrategy() 
	{ 
		eventManager = EventManager.getInstance();
		scanner = new Scanner(System.in);
		eventManager.subscribe(EventType.CALCULATE, this);
	}

	public void onEvent(Event event) 
	{ 
		if(event.type() == EventType.CALCULATE)
		{
			System.out.print("Enter the bank code: ");
			String code = scanner.nextLine().trim();
			System.out.print("Enter the account number: ");
			String account = scanner.nextLine().trim();

			if(code.matches("\\d{3}") && account.matches("\\d{12}"))
				calculate(Double.valueOf(event.data().toString()));
			else
				System.out.println("Pay Failed!"); 

		}
	}

	public String getCalculate()
	{
		return ans;
	}

	/**
	 * Bank code are 3 digits.
	 * Account number are 12 digits.
	 */
	@Override
	public void calculate(double totalPrice) 
	{ 
		DecimalFormat f = new DecimalFormat("###.00");
    	String str = "$" + f.format((totalPrice + 0.49));

		ans = str;

    	eventManager.publish(new StringEvent(EventType.PRINT_RECEIPT, str));

    	System.out.println("Pay successfully!"); 
	}	
}
