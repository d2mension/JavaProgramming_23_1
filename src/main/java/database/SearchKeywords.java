package database;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.sql.*;

import java.lang.String;

public class SearchKeywords {
	private String searchWord = null;
	
	public SearchKeywords(String searchWord) {
		this.searchWord = searchWord;
	}
	
	public String getSearchWord() {
		return this.searchWord;
	}
	
	public List<Integer> keywordSearch () {
		Scanner stdin = new Scanner(System.in);
		
		while (true) {
			try {
				HttpClient client = HttpClient.newHttpClient();
				
				KeywordDataGet sw = new KeywordDataGet(this.getSearchWord());
				
				List<Integer> result = sw.search();
				
				
				if (result.size() == 0) {
					JSONObject data = new JSONObject();
			        data.put("word", searchWord);
			        
			        HttpRequest request = HttpRequest.newBuilder()
			                .uri(URI.create("http://127.0.0.1:5000/similarity"))
			                .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
			                .header("Content-Type", "application/json")
			                .build();
			        
			        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
		
			        HttpResponse<String> response = responseFuture.join();
			        String responseBody = response.body();
			        
			        JSONParser parser = new JSONParser();
			        JSONObject jsonResponse = (JSONObject) parser.parse(responseBody);
			        String mostSimilarWord = (String) jsonResponse.get("most_similar_word");
			        
			        System.out.println(mostSimilarWord);
			        sw.setWord(mostSimilarWord);
			        result = sw.search();
				}
				
				return result;
		        
			} catch (Exception e) {e.printStackTrace();}
	        
		}
		
	}
}
