# Weather Application

A Java-based weather application with a graphical user interface that fetches and displays current weather data for a given location.

## Features

- **Search for Weather**: Enter a location and retrieve weather details including temperature, weather conditions, humidity, and wind speed.
- **Dynamic Updates**: Updates the display based on the location entered by the user.

## Components

- **`WeatherApplication`**: Main GUI class that provides the user interface for interacting with the weather data.
- **`WeatherDataHandler`**: Manages updates to the weather display.
- **`WeatherDataParser`**: Parses weather data received from the external API.
- **`LocationDataProvider`**: Provides location data based on the location name.
- **`WeatherDataProvider`**: Fetches weather data from the external weather API.
- **`ImageLoader`**: Loads images used in the application interface.
