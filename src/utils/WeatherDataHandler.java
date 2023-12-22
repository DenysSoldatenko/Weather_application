package utils;

import static java.util.Objects.requireNonNull;
import static utils.ImageLoader.loadImage;

import javax.swing.JLabel;
import org.json.simple.JSONObject;

/**
 * Utility class to handle weather data and update weather display components.
 */
public class WeatherDataHandler {

  /**
   * Updates the weather display components based on the user input.
   *
   * @param userInput             the user input for weather location
   * @param weatherConditionImage the JLabel for displaying weather condition image
   * @param temperatureLabel     the JLabel for displaying temperature
   * @param weatherConditionLabel the JLabel for displaying weather condition text
   * @param humidityLabel         the JLabel for displaying humidity
   * @param windSpeedLabel        the JLabel for displaying wind speed
   */
  public static void updateWeatherDisplay(String userInput, JLabel weatherConditionImage,
                                          JLabel temperatureLabel, JLabel weatherConditionLabel,
                                          JLabel humidityLabel, JLabel windSpeedLabel) {
    if (isInputEmpty(userInput)) {
      return;
    }

    JSONObject weatherData = WeatherApp.getWeatherData(userInput);
    String weatherCondition = (String) requireNonNull(weatherData).get("weather_condition");

    switch (weatherCondition) {
      case "Clear":
        weatherConditionImage.setIcon(loadImage("external/photos/sun.png"));
        break;
      case "Cloudy":
        weatherConditionImage.setIcon(loadImage("external/photos/clouds.png"));
        break;
      case "Rain":
        weatherConditionImage.setIcon(loadImage("external/photos/rain.png"));
        break;
      case "Snow":
        weatherConditionImage.setIcon(loadImage("external/photos/snow.png"));
        break;
      default:
        throw new IllegalArgumentException("Unknown weather condition: " + weatherCondition);
    }

    double temperature = (double) weatherData.get("temperature");
    temperatureLabel.setText(temperature + " C");
    weatherConditionLabel.setText(weatherCondition);

    long humidity = (long) weatherData.get("humidity");
    humidityLabel.setText("<html><b>Humidity</b> " + humidity + "%</html>");

    double windSpeed = (double) weatherData.get("windspeed");
    windSpeedLabel.setText("<html><b>Wind speed</b> " + windSpeed + "km/h</html>");
  }

  private static boolean isInputEmpty(String userInput) {
    return userInput.replaceAll("\\s", "").isEmpty();
  }
}
