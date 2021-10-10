package telran.currency.service;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public abstract class AbstractCurrencyConverter extends CurrencyCountryMapper {

	protected Map<String, Double> rates;
	
	@Override
	public double convert(String currencyCountryFrom, String currencyCountryTo, int amount) {
		refresh();
		double rateFrom = getRate(currencyCountryFrom.toUpperCase());
		double rateTo = getRate(currencyCountryTo.toUpperCase());
		return (amount * rateTo) / rateFrom;
	}
	
	
	protected Double getRate(String currencyCodeCountry) {
	    try {
	      return rates.containsKey(currencyCodeCountry)
	          ? rates.get(currencyCodeCountry)
	          : rates.get(countryCurrency.get(currencyCodeCountry));
	    } catch (Exception e) {
	      throw new RuntimeException("Wrong either currency code or country name");
	    } 
	  }
	
	private Map<String, List<String>> getCurrencyList(int amount, boolean strongestOrWeakest) {
		return rates.entrySet().stream().filter(e -> currencyCountries.containsKey(e.getKey()))
				.sorted((e1, e2) -> getComparator(strongestOrWeakest).compare(e1, e2))
				.limit(amount).collect(Collectors.toMap(e -> e.getKey() + "-" + e.getValue(),
						e -> currencyCountries.get(e.getKey()), (a, b) -> a, LinkedHashMap::new));
	}
	private Comparator<Entry<String, Double>> getComparator(boolean isAsc) {
		Comparator<Entry<String, Double>> comp = Entry.comparingByValue();
		return isAsc ? comp : comp.reversed();
	}
	@Override 
	public Map<String, List<String>> strongestCurrencies(int amount) {
		return getCurrencyList(amount, true);
	}
	@Override
	public Map<String, List<String>> weakestCurrencies(int amount) {
		return getCurrencyList(amount, false);
	}

	protected abstract void refresh();
	
	protected AbstractCurrencyConverter(Map<String, Double> rates) {
		this.rates = rates;
	}

}
