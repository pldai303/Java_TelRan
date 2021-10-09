package telran.currency.service;

import java.util.*;
import java.util.stream.Collectors;

import java.io.*;

public abstract class CurrencyCountryMapper implements CurrencyConverter {

	protected Map<String, String> countryCurrency;
	protected Map<String, List<String>> currencyCountries;

	protected CurrencyCountryMapper() {
		createCountryCurrency();
		createCurrencyCountries();
	}

	private void createCurrencyCountries() {
		currencyCountries = countryCurrency.entrySet().stream().collect(
				Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
	}

	private void createCountryCurrency() {
		try (BufferedReader reader = new BufferedReader(getResourceReader())) {
			countryCurrency = reader.lines().map(line -> line.toUpperCase().split("\t"))
					.filter(array -> array.length > 2)
					.collect(Collectors.toMap(array -> array[0], array -> array[2], (k1, k2) -> k1));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private Reader getResourceReader() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/codes-all_csv.txt");
		return new InputStreamReader(inputStream);
	}

	@Override
	public String getCurrencyCode(String currencyOrCountry) {
		currencyOrCountry = currencyOrCountry.toUpperCase();
		String res = currencyCountries.containsKey(currencyOrCountry) ? currencyOrCountry
				: countryCurrency.get(currencyOrCountry);
		if (res == null) {
			throw new RuntimeException("Eather code or country wrong");
		}
		return res;
	}

	@Override
	public List<String> getCountriesCurrency(String currency) {
		currency = currency.toUpperCase();
		List<String> res = currencyCountries.get(currency);
		if (res == null) {
			throw new RuntimeException("Incorrect code");
		}
		return res;
	}

}
