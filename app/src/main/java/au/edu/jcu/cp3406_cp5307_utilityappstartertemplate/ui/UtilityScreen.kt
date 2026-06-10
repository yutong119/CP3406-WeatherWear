package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model.OutfitRecommendation

@Composable
fun UtilityScreen(
    city: String,
    temperatureCelsius: Int,
    weatherCondition: String,
    displayedTemperature: String,
    comfortLabel: String,
    clothingSensitivity: String,
    outfitRecommendation: OutfitRecommendation
) {
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