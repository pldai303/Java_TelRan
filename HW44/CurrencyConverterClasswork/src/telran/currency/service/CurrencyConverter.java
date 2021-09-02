package telran.currency.service;
import java.util.*;

public interface CurrencyConverter {

	public double convert (String currencyCountryFrom, String currencyCountryTo, int amount);
	public Map<String, List<String>> strongestCurrencies(int amount);
	public Map<String, List<String>> weakestCurrencies(int amount);
}
