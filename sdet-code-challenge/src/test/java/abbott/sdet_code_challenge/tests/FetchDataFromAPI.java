package abbott.sdet_code_challenge.tests;

import java.lang.System;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import java.io.BufferedReader;

@Test(testName="TC.3 Fetch data from API endpoint and display output")
public class FetchDataFromAPI {

	private final static String USER_AGENT = "Mozilla/5.0";

	private final static String GET_URL = "https://datausa.io/api/data?drilldowns=Nation&measures=Population";

	public static void main(String[] args) throws Exception {

		// HTTP GET call to URL
		String responseBody = sendGET();
		// System.out.println("GET API DONE");

		// Using the JSON simple library parse the string into a json object
		JSONParser parse = new JSONParser();
		JSONObject json_obj = (JSONObject) parse.parse(responseBody);

		// Get the required object from the above created object
		JSONArray source = (JSONArray) json_obj.get("source");
		JSONArray data = (JSONArray) json_obj.get("data");

		JSONObject source_obj = null;
		for (int i = 0; i < source.size(); i++) {
			source_obj = (JSONObject) source.get(i);
		}
		JSONObject annotations = (JSONObject) source_obj.get("annotations");
		// Find name of census data
		String src = annotations.get("source_name").toString();
		// Find number of years
		Integer total = data.size();

		int max_year = 0;
		int min_year = 9999;
		float peak_percentage = 0;
		int peak_year = 0;
		float low_percentage = 100;
		int low_year = 9999;

		// get size of array
		int size_array = data.size();
		// loop through array to find remaining fields
		for (int i = 0; i < size_array; i++) {
			// get current year object data
			JSONObject new_obj = (JSONObject) data.get(i);
			int current_year = Integer.parseInt(new_obj.get("ID Year").toString());

			// Check if minimum year
			if (current_year < min_year)
				min_year = current_year;

			// Check if maximum year
			if (current_year > max_year)
				max_year = current_year;

			// Calculate percentage increase from previous year (i+1) element
			// Check if (i+1) does not exceed array
			// Look for peak and lowest percentage growth
			if (i + 1 < size_array) {
				JSONObject prev_year = (JSONObject) data.get(i + 1);
				float prev_population = Float.parseFloat(prev_year.get("Population").toString());
				float current_population = Float.parseFloat(new_obj.get("Population").toString());
				// calculate percentage increase of current year
				float current_percent = ((current_population - prev_population) / prev_population) * 100;
				// check for peak percentage
				if (current_percent > peak_percentage) {
					peak_percentage = current_percent;
					peak_year = current_year;
				}
				// check for lowest percentage
				if (current_percent < low_percentage) {
					low_percentage = current_percent;
					low_year = current_year;
				}
			}
		}

		String output = "According to " + src + ", in " + Integer.toString(total) + " years from "
				+ Integer.toString(min_year) + " to " + Integer.toString(max_year) + ", peak population growth was "
				+ Float.toString(peak_percentage) + "% in " + Integer.toString(peak_year)
				+ " and lowest population increase was " + Float.toString(low_percentage) + "% in "
				+ Integer.toString(low_year) + ". ";
		System.out.println(output);
	}

	private static String sendGET() throws Exception {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} else {
			throw new Exception("GET request not worked");
		}
	}

}
