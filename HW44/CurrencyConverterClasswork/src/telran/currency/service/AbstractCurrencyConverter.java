package telran.currency.service;

import java.util.*;
import java.util.Map.Entry;


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
		Map<String, List<String>> res = new LinkedHashMap<String, List<String>>();
		List<Map.Entry<String, Double>> tempCodeEuro = null;
		if (strongestOrWeakest == false)
		{
		tempCodeEuro = rates.entrySet().stream()
				.sorted(Map.Entry.<String, Double>comparingByValue().reversed()).toList();
		} else {
			tempCodeEuro = rates.entrySet().stream()
					.sorted(Map.Entry.<String, Double>comparingByValue()).toList();	
		}
			
		List<Map.Entry<String, String>> tempCountryCode = countryCurrency.entrySet().stream().toList();
		String codeISO = "";
		Integer i = 0;
		for (Entry <String, Double> entryCodeEuro : tempCodeEuro) {
			List<String> list = new ArrayList<String>();
			codeISO = entryCodeEuro.getKey(); 
			for (Map.Entry<String, String> entryCountriesCode : tempCountryCode) {
				if (codeISO.equals(entryCountriesCode.getValue())) {
				list.add(entryCountriesCode.getKey());
				}
			}
			
			if (list.size() > 0) {
				res.put(codeISO + " [" + entryCodeEuro.getValue() + "] ", list);
				i++;
				amount--;
				if (amount == 0) {
					break;
				}
			}
		}
		return res;
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
