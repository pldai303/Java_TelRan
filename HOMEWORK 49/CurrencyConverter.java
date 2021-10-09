package telran.currency.service;
import java.util.*;

public interface CurrencyConverter {

	public double convert (String currencyCountryFrom, String currencyCountryTo, int amount, Integer timeRefreshing);
	public Map<String, List<String>> strongestCurrencies(int amount);
	public Map<String, List<String>> weakestCurrencies(int amount);
	String getCurrencyCode(String currencyOrCountry);
	List<String> getCountriesCurrency(String currency);
}
