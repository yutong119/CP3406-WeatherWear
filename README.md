# WeatherWear

WeatherWear is a utility-style Android app that provides simple clothing advice based on live weather data.

## Purpose

The app helps users quickly understand what to wear for the day by converting weather information into clear outfit recommendations.

## Features

- Live weather data using Open-Meteo API
- Location, temperature, and weather condition cards
- Outfit recommendation based on temperature and weather
- Temperature comfort indicator
- Settings screen for city, temperature unit, clothing sensitivity, and umbrella advice
- Loading, error, and success states for API requests

## Technologies Used

- Kotlin
- Jetpack Compose
- Material Design 3
- ViewModel
- Repository pattern
- Retrofit
- Open-Meteo API（WeatherWear uses the Open-Meteo API because it provides free live weather data without requiring an API key. This makes the project easier to run and avoids exposing sensitive API credentials in the GitHub repository.）

## Architecture

The app is organised into separate layers:

- `ui`: Compose screens and reusable UI components
- `viewmodel`: UI state and app logic
- `data`: Repository and API service
- `model`: Data classes for UI state, outfit recommendation, and API response

The app uses a ViewModel and Repository pattern to separate UI state, business logic, and data fetching. For this assignment, the repository is created directly inside the ViewModel rather than using a full dependency injection framework such as Hilt, because the app scope is small.

## How to Run

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Sync Gradle.
4. Run the app on an Android emulator or physical device.
5. Use the Settings screen to enter a supported city and refresh the weather.

## Supported Cities

The app uses the Open-Meteo Geocoding API to convert city names into coordinates, so users can search for many cities instead of only predefined locations.

## API

WeatherWear uses the Open-Meteo API to retrieve current temperature and weather code data. The API does not require an API key.

## Known Limitations

- Only selected cities are currently supported.
- Weather data depends on internet connection.
- Outfit recommendations are rule-based rather than personalised by user profile.
- - If multiple cities share the same name, the app uses the first result returned by the geocoding API.
- Weather data depends on internet connection.

## Future Improvements

- Add search support for more cities.
- Improve visual outfit illustrations.
- Add more detailed weather information, such as wind and rain probability.
- Save user settings locally.
