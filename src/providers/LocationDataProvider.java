package providers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Utility class for fetching location data from an external API.
 */
public class LocationDataProvider {
  private static final Logger logger = Logger.getLogger(LocationDataProvider.class.getName());

  /**
   * Retrieves location data from the API for the specified location name.
   *
   * @param locationName the name of the location to fetch data for
   * @return a JSONArray containing location data, or null if an error occurs
   */
  public static JSONArray getLocationData(String locationName) {
    try {
      String apiUrl = buildApiUrl(locationName);
      HttpURLConnection connection = fetchApiResponse(apiUrl);

      if (connection.getResponseCode() != 200) {
        logger.log(Level.SEVERE, "Error: Could not connect to the location API");
        return null;
      }

      StringBuilder jsonResponse = new StringBuilder();
      try (Scanner scanner = new Scanner(connection.getInputStream())) {
        while (scanner.hasNext()) {
          jsonResponse.append(scanner.nextLine());
        }
      }

      connection.disconnect();

      JSONParser parser = new JSONParser();
      JSONObject resultsJson = (JSONObject) parser.parse(jsonResponse.toString());

      return (JSONArray) resultsJson.get("results");
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error fetching location data: " + e.getMessage(), e);
    }

    return null;
  }

  private static String buildApiUrl(String locationName) {
    locationName = locationName.replaceAll(" ", "+");
    return "https://geocoding-api.open-meteo.com/v1/search?name="
      + locationName + "&count=10&language=en&format=json";
  }

  private static HttpURLConnection fetchApiResponse(String apiUrl) throws IOException {
    URL url = URI.create(apiUrl).toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.connect();
    return connection;
  }
}
