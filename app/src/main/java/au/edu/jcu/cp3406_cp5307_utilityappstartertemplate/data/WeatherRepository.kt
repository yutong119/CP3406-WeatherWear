package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.data

import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model.OutfitRecommendation

class WeatherRepository {

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

        val needsUmbrella = weatherCondition.contains("Rain", ignoreCase = true)

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