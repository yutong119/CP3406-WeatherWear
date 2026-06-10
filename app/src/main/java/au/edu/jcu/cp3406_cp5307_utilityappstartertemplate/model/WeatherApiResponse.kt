package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model

data class WeatherApiResponse(
    val latitude: Double,
    val longitude: Double,
    val current: CurrentWeather
)

data class CurrentWeather(
    val temperature_2m: Double,
    val weather_code: Int
)