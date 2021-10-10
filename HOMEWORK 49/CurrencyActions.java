package telran.currency.controller.actions;

import java.io.*;
import java.util.*;



import telran.currency.service.*;

import telran.view.*;

public class CurrencyActions {
	
	private static InputOutput io;
	private static AbstractCurrencyConverter currencyService;
	private static String fileName = "config.dat";
	
	
	
	public static Item[] getCurrencyItems(AbstractCurrencyConverter currencyConverter) throws Exception {
		currencyService =  currencyConverter; 
		return getItems();
	}
	
	public CurrencyActions(InputOutput io) {
		this.io = io;
	}
	
	

	public static Item[] getItems() throws Exception {
		Item convertItem = Item.of("Convert", CurrencyActions::displayConvertion);
		Item strongestCurr = Item.of("Countries with Strongest Currency", CurrencyActions::displayStrongestCurrencies);
		Item weekestCurr = Item.of("Countries with Weakest Currency", CurrencyActions::displayWeekestCurrencies);
		Item keySettings = Item.of("Configuration", CurrencyActions::SetApiKey);
		Item curCodeOfCountry = Item.of("Display currency code of a given country ", CurrencyActions::displayCurrencyCodeByCountry);
		Item countryByCurrency = Item.of("Display countries using a given currency ",CurrencyActions::displayCountryByCurrencyCode);
		Item exitAndSave = Item.of("Exit", null); 
		Item [] items = {keySettings, convertItem, strongestCurr, weekestCurr, curCodeOfCountry, countryByCurrency, exitAndSave};
		return items;
	}
	
	private static void setInfoByKey(String key) {
		if (CurrencyConverterFixerImpl.basicConfig.containsKey(key))
			CurrencyConverterFixerImpl.basicConfig.put(key, io.readString("Currenet " + key + " is " + CurrencyConverterFixerImpl.basicConfig.get(key))); 
			else 
				CurrencyConverterFixerImpl.basicConfig.put(key, io.readString(io.readString("Enter " + key)));
	}
	
	private static void saveToFile() throws Exception {
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
			writer.writeObject(CurrencyConverterFixerImpl.basicConfig);
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("unchecked")
	private static void loadFromFile() throws Exception {
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
			Object result = reader.readObject();
			CurrencyConverterFixerImpl.basicConfig = (HashMap<String, String>) result;
		} catch (Exception e) {

		}
	}
	
	
	private static void createOrLoadFile() {
		File myFile = new File(fileName);
		if (!myFile.exists()) {
			try {
				myFile.createNewFile();
			} catch (Exception e) {

			}
		} else {
			try {
				loadFromFile();
			} catch (Exception e) {

			}
		}
	}
	
	
	public static void SetApiKey(InputOutput io) {
		createOrLoadFile();
		setInfoByKey("API key");
		setInfoByKey("time refreshing");
		try {
		saveToFile(); 
		} catch (Exception e) {
			
		}
	}
	
		
	public static void displayCountryByCurrencyCode(InputOutput io) {
		String currencyCode = io.readString("Enter currency code:");
		currencyService.getCountriesCurrency(currencyCode).stream().forEach(n -> io.writeObjectLine(n));
	}
	
	public static void displayCurrencyCodeByCountry(InputOutput io) {
		String countryName = io.readString("Enter country name:");
		io.writeObjectLine(currencyService.getCurrencyCode(countryName));
	}
	 
	public static void displayStrongestCurrencies(InputOutput io) {
		int amount = io.readInt("How many currencies you want to display? : ");
		currencyService.strongestCurrencies(amount).entrySet().stream().forEach(n -> io.writeObjectLine(n));
	}
	
	public static void displayWeekestCurrencies(InputOutput io) {
		int amount = io.readInt("How many currencies you want to display? : ");
		currencyService.weakestCurrencies(amount).entrySet().stream().forEach(n -> io.writeObjectLine(n));
	}
	
	private static void displayConvertion(InputOutput io) {
		System.out.printf("You will get %.2f \n", convert());
	}
	
	
	private static Double convert () {
		String from = io.readString("Enter currency code/name FROM convertation "); 
		String to = io.readString("Enter currency code/name TO convertation "); 
		int amount = io.readInt("How much money do you want to convert? :");
		return  currencyService.convert(from, to, amount);
	}
	

	
	
}
