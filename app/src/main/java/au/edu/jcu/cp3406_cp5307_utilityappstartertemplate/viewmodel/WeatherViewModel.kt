package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.data.WeatherRepository
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model.WeatherUiState

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    var uiState by mutableStateOf(WeatherUiState())
        private set

    fun updateCity(newCity: String) {
        uiState = uiState.copy(city = newCity)
    }

    fun updateUmbrellaAdvice(enabled: Boolean) {
        uiState = uiState.copy(showUmbrellaAdvice = enabled)
    }

    fun updateTemperatureUnit(newUnit: String) {
        uiState = uiState.copy(temperatureUnit = newUnit)
    }

    fun updateClothingSensitivity(newSensitivity: String) {
        uiState = uiState.copy(clothingSensitivity = newSensitivity)
    }

    fun getDisplayedTemperature(): String {
        return repository.formatTemperature(
            temperatureCelsius = uiState.temperatureCelsius,
            temperatureUnit = uiState.temperatureUnit
        )
    }

    fun getComfortLabel(): String {
        return repository.getComfortLabel(uiState.temperatureCelsius)
    }

    fun getOutfitRecommendation() =
        repository.getOutfitRecommendation(
            temperatureCelsius = uiState.temperatureCelsius,
            clothingSensitivity = uiState.clothingSensitivity,
            showUmbrellaAdvice = uiState.showUmbrellaAdvice,
            weatherCondition = uiState.weatherCondition
        )
}