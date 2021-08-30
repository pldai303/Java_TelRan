package telran.currency.controller.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import telran.currency.services.CurrencyMethods;
import telran.currency.services.CurrencyMethodsMapImpl;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CurrencyActions {

	private static Map<String, String> countriesAndCodes;
	private static Map<String, Double> CodesAndUnit;
	private static CurrencyMethodsMapImpl currencyMethodsImpl;
	private static InputOutput io;
	
	
	public CurrencyActions(InputOutput io) {
		currencyMethodsImpl = new CurrencyMethodsMapImpl();
		CodesAndUnit = currencyMethodsImpl.getCodesAndUnits();
		countriesAndCodes = currencyMethodsImpl.getCountriesAndCodes();
		this.io = io;
	}
	
	private Map<String, Double> getCodesAndUnit() {
		return CodesAndUnit;
	}
	
	private  Map<String, String> getCountriesAndCodes() {
		return countriesAndCodes;
	}
	
	public static void displayStrongestCurrencies(InputOutput io) {
		int amount = io.readInt("How many currencies you want to display? : ");
		CodesAndUnit.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue()).limit(amount).forEach(System.out::println);
	}
	
	public static void displayWeekestCurrencies(InputOutput io) {
		int amount = io.readInt("How many currencies you want to display? : ");
		CodesAndUnit.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(amount).forEach(System.out::println);
	}
	
	
	private static Double convert () {
		String from = io.readString("Enter currency code/name FROM convertation "); 
		String to = io.readString("Enter currency code/name TO convertation "); 
		int amount = io.readInt("How much money do you want to convert? :");
		return currencyMethodsImpl.convert(from, to, amount);
	}
	
	private static void displayConvertion(InputOutput io) {
		System.out.printf("You will get %.3f \n", convert());
	}
	
	
	public static Item[] getCurrencyItems(CurrencyMethods currencyService) {
		return getItems();
	}

	public static Item[] getItems() {
		Item convertItem = Item.of("Convert", CurrencyActions::displayConvertion);
		Item strongestCurr = Item.of("Countries with Strongest Currency", CurrencyActions::displayStrongestCurrencies);
		Item weekestCurr = Item.of("Countries with Weakest Currency",CurrencyActions::displayWeekestCurrencies);
		Item exitAndSave = Item.of("Exit", null); 
		Item [] items = {convertItem, strongestCurr, weekestCurr,  exitAndSave};
		return items;
	}
	
	
}
