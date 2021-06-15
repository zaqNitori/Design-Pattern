package org.ntutssl.shop;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PayByCreditCardStrategy implements PayStrategy, EventListener 
{
	private EventManager eventManager;
	private Scanner scanner;
	private String ans;

	public PayByCreditCardStrategy() 
	{ 
		eventManager = EventManager.getInstance();
		scanner = new Scanner(System.in);
		eventManager.subscribe(EventType.CALCULATE, this);
	}

	public void onEvent(Event event) 
	{ 
		if(event.type() == EventType.CALCULATE)
		{	  
			System.out.print("Enter the card number: ");
			String card = scanner.nextLine().trim();
			System.out.print("Enter the card expiration date 'mm/yy': ");
			String date = scanner.nextLine().trim();
			System.out.print("Enter the CVV code: ");
			String cvv = scanner.nextLine().trim();
	  
			if(card.matches("\\d{16}") && date.matches("((0[1-9])|(1[0-2]))/\\d{2}") && cvv.matches("\\d{3}"))
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
	 * Card number are 16 digits.
	 * The format of card expiration date is 'mm/yy', such as '06/21'.
	 * CVV code are 3 digits.
	 */
	@Override
	public void calculate(double totalPrice) 
	{ 
		DecimalFormat f = new DecimalFormat("###.00");
		String str = "$" + f.format(totalPrice - (totalPrice * 0.1));

		ans = str;

		eventManager.publish(new StringEvent(EventType.PRINT_RECEIPT, str));

		System.out.println("Pay successfully!"); 
	}


}
