package telran.currency.controller;

import telran.currency.controller.actions.CurrencyActions;
import telran.currency.service.AbstractCurrencyConverter;
import telran.currency.service.CurrencyConverter;
import telran.currency.service.CurrencyConverterFileImpl;
import telran.currency.service.CurrencyCountryMapper;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CurrencyServiceAppl {

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		CurrencyActions ca = new CurrencyActions(io);
//		//SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS	No universal currency
		AbstractCurrencyConverter currencyService = null;
		Item[] items = CurrencyActions.getCurrencyItems();
		Menu menu = new Menu("Convert Menu", items);
		try {
			menu.perform(io);
		} catch (Exception e) {

		}

	}

}
 