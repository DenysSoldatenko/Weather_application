package parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Utility class for parsing weather data from JSON format.
 */
public class WeatherDataParser {

  /**
  * Parses the weather data from JSON format.
  *
  * @param jsonData the JSON data containing weather information
  * @return a JSONObject containing parsed weather data
  * @throws ParseException if there's an error parsing the JSON data
  */
  public static JSONObject parseWeatherData(String jsonData) throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject resultJsonObj = (JSONObject) parser.parse(jsonData);

    JSONObject hourlyData = (JSONObject) resultJsonObj.get("hourly");

    JSONArray timeList = (JSONArray) hourlyData.get("time");
    int currentIndex = findIndexOfCurrentTime(timeList);

    JSONArray temperatureData = (JSONArray) hourlyData.get("temperature_2m");
    double currentTemperature = (double) temperatureData.get(currentIndex);

    JSONArray weatherCodeData = (JSONArray) hourlyData.get("weathercode");
    String weatherCondition = extractWeatherCondition((long) weatherCodeData.get(currentIndex));

    JSONArray humidityData = (JSONArray) hourlyData.get("relativehumidity_2m");
    long currentHumidity = (long) humidityData.get(currentIndex);

    JSONArray windSpeedData = (JSONArray) hourlyData.get("windspeed_10m");
    double currentWindSpeed = (double) windSpeedData.get(currentIndex);

    JSONObject weatherData = new JSONObject();
    weatherData.put("temperature", currentTemperature);
    weatherData.put("weather_condition", weatherCondition);
    weatherData.put("humidity", currentHumidity);
    weatherData.put("windspeed", currentWindSpeed);

    return weatherData;
  }

  private static int findIndexOfCurrentTime(JSONArray timeList) {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");
    String currentTime = currentDateTime.format(formatter);

    for (int i = 0; i < timeList.size(); i++) {
      String time = (String) timeList.get(i);
      if (time.equalsIgnoreCase(currentTime)) {
        return i;
      }
    }

    return 0;
  }

  /**
   * Extracts the weather condition from the weather code.
   *
   * @param weatherCode the weather code
   * @return the weather condition
  */
  public static String extractWeatherCondition(long weatherCode) {
    if (weatherCode == 0L) {
      return "Clear";
    } else if (weatherCode > 0L && weatherCode <= 3L) {
      return "Cloudy";
    } else if ((weatherCode >= 51L && weatherCode <= 67L)
        || (weatherCode >= 80L && weatherCode <= 99L)) {
      return "Rain";
    } else if (weatherCode >= 71L && weatherCode <= 77L) {
      return "Snow";
    }
    return "";
  }
}
