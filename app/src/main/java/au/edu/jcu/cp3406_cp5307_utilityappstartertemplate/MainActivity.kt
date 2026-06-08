package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.theme.CP3406_CP5603UtilityAppStarterTemplateTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.RadioButton
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CP3406_CP5603UtilityAppStarterTemplateTheme {
                UtilityApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UtilityAppPreview() {
    CP3406_CP5603UtilityAppStarterTemplateTheme {
        UtilityApp()
    }
}
@Composable
fun UtilityApp() {
    var selectedTab by remember { mutableStateOf("Utility") }
    var city by remember { mutableStateOf("Singapore") }
    var showUmbrellaAdvice by remember { mutableStateOf(true) }
    var temperatureUnit by remember { mutableStateOf("Celsius") }
    var clothingSensitivity by remember { mutableStateOf("Normal") }

    Scaffold(
        containerColor = Color(0xFFE3F2FD),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Utility") },
                    label = { Text("Utility") },
                    selected = selectedTab == "Utility",
                    onClick = { selectedTab = "Utility" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selectedTab == "Settings",
                    onClick = { selectedTab = "Settings" }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "Utility" -> UtilityScreen(
                    city = city,
                    temperatureUnit = temperatureUnit,
                    clothingSensitivity = clothingSensitivity,
                    showUmbrellaAdvice = showUmbrellaAdvice
                )

                "Settings" -> SettingsScreen(
                    city = city,
                    onCityChange = { city = it },
                    showUmbrellaAdvice = showUmbrellaAdvice,
                    onShowUmbrellaAdviceChange = { showUmbrellaAdvice = it },
                    temperatureUnit = temperatureUnit,
                    onTemperatureUnitChange = { temperatureUnit = it },
                    clothingSensitivity = clothingSensitivity,
                    onClothingSensitivityChange = { clothingSensitivity = it }
                )
            }
        }
    }
}

@Composable
fun UtilityScreen(
    city: String,
    temperatureUnit: String,
    clothingSensitivity: String,
    showUmbrellaAdvice: Boolean
) {
    val temperatureCelsius = 31
    val weatherCondition = "Sunny ☀️"

    val displayedTemperature = formatTemperature(
        temperatureCelsius = temperatureCelsius,
        temperatureUnit = temperatureUnit
    )

    val outfitRecommendation = getOutfitRecommendation(
        temperatureCelsius = temperatureCelsius,
        clothingSensitivity = clothingSensitivity,
        showUmbrellaAdvice = showUmbrellaAdvice,
        weatherCondition = weatherCondition
    )

    val comfortLabel = getComfortLabel(temperatureCelsius)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "WeatherWear",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Simple clothing advice based on today's weather.",
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            WeatherInfoCard(
                title = "Location",
                value = city.ifBlank { "Unknown" },
                modifier = Modifier.weight(1f)
            )

            WeatherInfoCard(
                title = "Temp",
                value = displayedTemperature,
                modifier = Modifier.weight(1f)
            )

            WeatherInfoCard(
                title = "Weather",
                value = weatherCondition,
                modifier = Modifier.weight(1f)
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Recommended outfit",
                    style = MaterialTheme.typography.titleMedium
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = outfitRecommendation.characterPreview,
                        style = MaterialTheme.typography.displayMedium
                    )
                }

                Text(
                    text = outfitRecommendation.outfitName,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = outfitRecommendation.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Sensitivity: $clothingSensitivity",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Temperature comfort",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "$displayedTemperature feels ${comfortLabel.lowercase()} today",
                    style = MaterialTheme.typography.bodyMedium
                )

                ComfortTemperatureBar(
                    temperature = temperatureCelsius,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ComfortTemperatureBar(
    temperature: Int,
    modifier: Modifier = Modifier
) {
    val minTemp = -10
    val maxTemp = 40

    val progress = ((temperature - minTemp).toFloat() / (maxTemp - minTemp))
        .coerceIn(0f, 1f)

    val comfortLabel = getComfortLabel(temperature)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Comfort level", style = MaterialTheme.typography.titleSmall)
            Text(comfortLabel, style = MaterialTheme.typography.labelLarge)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .clip(RoundedCornerShape(50))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF4A90E2), // cold blue
                            Color(0xFF66BB6A), // comfortable green
                            Color(0xFFFF7043)  // hot red
                        )
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .offset(x = (progress * 250).dp)
                    .width(6.dp)
                    .height(18.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.9f))
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Cold", style = MaterialTheme.typography.labelSmall)
            Text("Comfort", style = MaterialTheme.typography.labelSmall)
            Text("Hot", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun WeatherInfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun SettingsScreen(
    city: String,
    onCityChange: (String) -> Unit,
    showUmbrellaAdvice: Boolean,
    onShowUmbrellaAdviceChange: (Boolean) -> Unit,
    temperatureUnit: String,
    onTemperatureUnitChange: (String) -> Unit,
    clothingSensitivity: String,
    onClothingSensitivityChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Location",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = city,
                    onValueChange = onCityChange,
                    label = { Text("City") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Weather preferences",
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Show umbrella advice")
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = showUmbrellaAdvice,
                        onCheckedChange = onShowUmbrellaAdviceChange
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Temperature unit",
                    style = MaterialTheme.typography.titleMedium
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = temperatureUnit == "Celsius",
                        onClick = { onTemperatureUnitChange("Celsius") }
                    )
                    Text("Celsius")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = temperatureUnit == "Fahrenheit",
                        onClick = { onTemperatureUnitChange("Fahrenheit") }
                    )
                    Text("Fahrenheit")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Clothing sensitivity",
                    style = MaterialTheme.typography.titleMedium
                )

                listOf("Cold easily", "Normal", "Hot easily").forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = clothingSensitivity == option,
                            onClick = { onClothingSensitivityChange(option) }
                        )
                        Text(option)
                    }
                }
            }
        }
    }
}

data class OutfitRecommendation(
    val outfitName: String,
    val description: String,
    val characterPreview: String
)

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