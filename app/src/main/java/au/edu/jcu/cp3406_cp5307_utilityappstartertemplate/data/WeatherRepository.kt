package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.data

import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model.OutfitRecommendation
import kotlin.math.roundToInt

class WeatherRepository {

    suspend fun fetchWeatherForCity(city: String): Pair<Int, String> {
        val coordinates = getCoordinatesForCity(city)
        val response = RetrofitInstance.weatherApi.getCurrentWeather(
            latitude = coordinates.first,
            longitude = coordinates.second
        )

        val temperature = response.current.temperature_2m.roundToInt()
        val weatherCondition = mapWeatherCodeToCondition(response.current.weather_code)
        return Pair(temperature, weatherCondition)
    }

    private fun getCoordinatesForCity(city: String): Pair<Double, Double> {
        return when (city.trim().lowercase()) {
            "singapore" -> Pair(1.3521, 103.8198)
            "tokyo" -> Pair(35.6762, 139.6503)
            "london" -> Pair(51.5072, -0.1276)
            "new york" -> Pair(40.7128, -74.0060)
            "sydney" -> Pair(-33.8688, 151.2093)
            "beijing" -> Pair(39.9042, 116.4074)
            "taipei" -> Pair(25.0330, 121.5654)
            else -> Pair(1.3521, 103.8198)
        }
    }

    private fun mapWeatherCodeToCondition(code: Int): String {
        return when (code) {
            0 -> "Sunny ☀️"
            1, 2 -> "Partly cloudy ⛅"
            3 -> "Cloudy ☁️"
            45, 48 -> "Foggy 🌫️"
            51, 53, 55, 56, 57 -> "Drizzle 🌦️"
            61, 63, 65, 66, 67 -> "Rainy 🌧️"
            71, 73, 75, 77 -> "Snowy ❄️"
            80, 81, 82 -> "Rain showers 🌧️"
            95, 96, 99 -> "Thunderstorm ⛈️"
            else -> "Unknown"
        }
    }

    fun formatTemperature(
        temperatureCelsius: Int,
        temperatureUnit: String
    ): String {
        return if (temperatureUnit == "Fahrenheit") {
            val fahrenheit = temperatureCelsius * 9 / 5 + 32
            "$fahrenheit°F"
        } else {
            "$temperatureCelsius°C"
        }
    }

    fun getComfortLabel(temperatureCelsius: Int): String {
        return when {
            temperatureCelsius <= 5 -> "Very cold"
            temperatureCelsius <= 17 -> "Cold"
            temperatureCelsius <= 25 -> "Comfortable"
            temperatureCelsius <= 31 -> "Warm"
            else -> "Very hot"
        }
    }

    fun getOutfitRecommendation(
        temperatureCelsius: Int,
        clothingSensitivity: String,
        showUmbrellaAdvice: Boolean,
        weatherCondition: String
    ): OutfitRecommendation {
        val adjustedTemperature = when (clothingSensitivity) {
            "Cold easily" -> temperatureCelsius - 3
            "Hot easily" -> temperatureCelsius + 3
            else -> temperatureCelsius
        }

        val baseRecommendation = when {
            adjustedTemperature >= 32 -> OutfitRecommendation(
                outfitName = "T-shirt and shorts",
                description = "It is very hot today. Wear light and breathable clothes.",
                characterPreview = "🧍‍♀️\n👕🩳"
            )

            adjustedTemperature >= 25 -> OutfitRecommendation(
                outfitName = "Light clothes",
                description = "It is warm today. A T-shirt or thin shirt should be comfortable.",
                characterPreview = "🧍‍♀️\n👕"
            )

            adjustedTemperature >= 18 -> OutfitRecommendation(
                outfitName = "Light jacket",
                description = "The weather is mild. A light jacket or cardigan is a safe choice.",
                characterPreview = "🧍‍♀️\n🧥"
            )

            adjustedTemperature >= 10 -> OutfitRecommendation(
                outfitName = "Sweater and jacket",
                description = "It is a little cold. Wear a sweater with a jacket.",
                characterPreview = "🧍‍♀️\n🧶🧥"
            )

            adjustedTemperature >= 0 -> OutfitRecommendation(
                outfitName = "Thick coat and scarf",
                description = "It is cold today. A thick coat and scarf will help you stay warm.",
                characterPreview = "🧍‍♀️\n🧥🧣"
            )

            else -> OutfitRecommendation(
                outfitName = "Down jacket and gloves",
                description = "It is freezing. Wear a down jacket, gloves, and a warm hat.",
                characterPreview = "🧍‍♀️\n🧥🧤"
            )
        }

        val needsUmbrella = weatherCondition.contains("Rain", ignoreCase = true) ||
                weatherCondition.contains("Drizzle", ignoreCase = true) ||
                weatherCondition.contains("Thunderstorm", ignoreCase = true)

        return if (showUmbrellaAdvice && needsUmbrella) {
            baseRecommendation.copy(
                outfitName = "${baseRecommendation.outfitName} + umbrella",
                description = "${baseRecommendation.description} Bring an umbrella because rain is expected.",
                characterPreview = "${baseRecommendation.characterPreview}\n☂️"
            )
        } else {
            baseRecommendation
        }
    }
}