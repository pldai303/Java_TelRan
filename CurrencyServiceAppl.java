package telran.currency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import telran.currency.controller.actions.CurrencyActions;
import telran.currency.services.CurrencyMethods;
import telran.view.*;

public class CurrencyServiceAppl {
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		CurrencyActions ca = new CurrencyActions(io);
//		//SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS	No universal currency
		CurrencyMethods currencyService = null;
		Item[] items = CurrencyActions.getCurrencyItems(currencyService);
	    Menu menu = new Menu("Convert Menu",items);
		try {
		menu.perform(io); 
		} catch (Exception e) {
			
		}
	}
}
