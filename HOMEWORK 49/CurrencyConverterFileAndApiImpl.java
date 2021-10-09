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




public class CurrencyConverterFileAndApiImpl extends AbstractCurrencyConverter {
	private static Integer timeStamp = 0; 
	private static String apiKey = "";
	
	public static CurrencyConverter getCurrencyConverter(String filePath) throws Exception{
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
			@SuppressWarnings("unchecked")
			Map<String, Double> rates = (Map<String, Double>) input.readObject();
			return new CurrencyConverterFileAndApiImpl(rates);
		}
		
	} 
	
	private static String getValueFromString(String str, String fieldName, String startSymbol, String finishSymbol) {
		int firstIdx = str.indexOf(fieldName);
		int secondIdx = str.indexOf(startSymbol, firstIdx);
		int lastIdx = str.indexOf(finishSymbol, secondIdx);
		return str.substring(secondIdx+1, lastIdx);
	} 
	
	
	private static HashMap<String, Double> getResponse(String apiKey) {
		CurrencyConverterFileAndApiImpl.apiKey = apiKey;
		HashMap<String, Double> rates = null;
		HttpResponse<String> response = null;
		try {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(new URI("http://data.fixer.io/api/latest?access_key=" + apiKey)).build(); 
		response = httpClient.send(request, BodyHandlers.ofString());
		if (Boolean.parseBoolean(getValueFromString(response.body(), "success", ":",","))) {
			timeStamp = Integer.parseInt(getValueFromString(response.body(), "timestamp",":",","));
			String ratesValue = getValueFromString(response.body(), "rates", "{", "}");
			rates = (HashMap<String, Double>) Arrays.asList(ratesValue.replaceAll("\"", "").split(",")).stream().
					map(s -> s.split(":")).collect(Collectors.toMap(e -> e[0], e -> Double.parseDouble(e[1])));
		}
		}
		catch (Exception e) {
		
		}
		return rates;
	}
	
	public static CurrencyConverter getCurrencyConverterFromApi(String apiKey) {
		return new CurrencyConverterFileAndApiImpl(getResponse(apiKey));
	}
	
	protected CurrencyConverterFileAndApiImpl(Map<String, Double> rates) {
		super(rates);
	}

	@Override
	protected void refresh(Integer timeRefreshing) {
		if (timeRefreshing + timeStamp < Instant.now().getEpochSecond()) {
			getResponse(apiKey);
			System.out.println("Data was updated NOW!");
		} 
		else {
			System.out.println("Data will be updated at " + LocalDateTime.ofInstant(Instant.ofEpochSecond(timeRefreshing + timeStamp), ZoneId.systemDefault()));
		}
		

	}

}
