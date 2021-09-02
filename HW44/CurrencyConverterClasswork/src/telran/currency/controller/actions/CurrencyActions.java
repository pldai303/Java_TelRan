package telran.currency.controller.actions;

import java.util.*;

import telran.currency.service.*;

import telran.view.*;

public class CurrencyActions {
	
	private static InputOutput io;
	private static Map<String, List<String>> countriesAndCodes;
	private static Map<String, Double> codesAndUnit;
	private static CurrencyConverterFileImpl currencyFileImpl;
	private static AbstractCurrencyConverter currencyService;
	
	public static Item[] getCurrencyItems() {
		
		return getItems();
	}
	
	public CurrencyActions(InputOutput io) {
		this.io = io;
		try { 
		currencyService =  (CurrencyConverterFileImpl)currencyFileImpl.getCurrencyConverter("rates_2021-08-28");
		} catch (Exception e) {

		}
	}

	public static Item[] getItems() {
		Item convertItem = Item.of("Convert", CurrencyActions::displayConvertion);
		Item strongestCurr = Item.of("Countries with Strongest Currency", CurrencyActions::displayStrongestCurrencies);
		Item weekestCurr = Item.of("Countries with Weakest Currency", CurrencyActions::displayWeekestCurrencies);
		Item exitAndSave = Item.of("Exit", null); 
		Item [] items = {convertItem, strongestCurr, weekestCurr,  exitAndSave};
		return items;
	}	
	 
	public static void displayStrongestCurrencies(InputOutput io) {
		int amount = io.readInt("How many currencies you want to display? : ");
		////CodesAndUnit.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue()).limit(amount).forEach(System.out::println);
		currencyService.strongestCurrencies(amount).entrySet().stream().forEach (System.out::println);
	}
	
	public static void displayWeekestCurrencies(InputOutput io) {
		int amount = io.readInt("How many currencies you want to display? : ");
		currencyService.weakestCurrencies(amount).entrySet().stream().forEach (System.out::println);
	}
	
	private static void displayConvertion(InputOutput io) {
		System.out.printf("You will get %.3f \n", convert());
	}
	
	
	private static Double convert () {
		
		String from = io.readString("Enter currency code/name FROM convertation "); 
		String to = io.readString("Enter currency code/name TO convertation "); 
		int amount = io.readInt("How much money do you want to convert? :");
		return  currencyService.convert(from, to, amount);
	}
	

	
	
}
