package providers;

import static java.net.URI.create;
import static java.util.Objects.requireNonNull;
import static java.util.logging.Level.SEVERE;
import static parsers.WeatherDataParser.parseWeatherData;
import static providers.LocationDataProvider.getLocationData;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * Utility class for fetching weather data from an external API.
 */
public class WeatherDataProvider {

  private static final Logger LOGGER = Logger.getLogger(WeatherDataProvider.class.getName());

  /**
   * Retrieves weather data for the specified location.
   *
   * @param locationName the name of the location to fetch weather data for
   * @return a JSONObject containing weather data, or null if an error occurs
   */
  public static JSONObject getWeatherData(String locationName) {
    try {
      JSONArray locationData = getLocationData(locationName);
      JSONObject location = (JSONObject) requireNonNull(locationData).get(0);
      double latitude = (double) location.get("latitude");
      double longitude = (double) location.get("longitude");
      String apiUrl = buildApiUrl(latitude, longitude);

      HttpURLConnection connection = fetchApiConnection(apiUrl);
      if (connection.getResponseCode() == 200) {
        return parseApiResponse(connection);
      } else {
        LOGGER.log(SEVERE, "Error: Could not connect to weather API");
      }
    } catch (IOException | ParseException e) {
      LOGGER.log(SEVERE, "Error while fetching weather data", e);
    }
    return null;
  }

  private static String buildApiUrl(double latitude, double longitude) {
    return "https://api.open-meteo.com/v1/forecast?"
      + "latitude=" + latitude
      + "&longitude=" + longitude
      + "&hourly=temperature_2m,relativehumidity_2m,weathercode,"
      + "windspeed_10m&timezone=Europe/Kyiv";
  }

  private static HttpURLConnection fetchApiConnection(String apiUrl) throws IOException {
    URL url = create(apiUrl).toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.connect();
    return connection;
  }

  private static JSONObject parseApiResponse(HttpURLConnection connection)
      throws IOException, ParseException {
    StringBuilder responseBuilder = new StringBuilder();
    try (Scanner scanner = new Scanner(connection.getInputStream())) {
      while (scanner.hasNext()) {
        responseBuilder.append(scanner.nextLine());
      }
    }
    connection.disconnect();
    return parseWeatherData(responseBuilder.toString());
  }
}
