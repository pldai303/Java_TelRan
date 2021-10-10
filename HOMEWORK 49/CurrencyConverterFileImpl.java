package telran.currency.service;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;




public class CurrencyConverterFileImpl extends AbstractCurrencyConverter {

	public static CurrencyConverter getCurrencyConverter(String filePath) throws Exception{
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
			@SuppressWarnings("unchecked")
			Map<String, Double> rates = (Map<String, Double>) input.readObject();
			return new CurrencyConverterFileImpl(rates);
		}
		
	} 
		
	protected CurrencyConverterFileImpl(Map<String, Double> rates) {
		super(rates);
	}

	@Override
	protected void refresh() {
		

	}

}
