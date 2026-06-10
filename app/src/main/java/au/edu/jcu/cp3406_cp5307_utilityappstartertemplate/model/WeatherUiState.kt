package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model

data class WeatherUiState(
    val city: String = "Singapore",
    val temperatureCelsius: Int = 31,
    val weatherCondition: String = "Sunny ☀️",
    val temperatureUnit: String = "Celsius",
    val clothingSensitivity: String = "Normal",
    val showUmbrellaAdvice: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)