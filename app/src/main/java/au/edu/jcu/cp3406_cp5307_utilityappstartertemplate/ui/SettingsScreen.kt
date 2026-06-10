package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button

@Composable
fun SettingsScreen(
    city: String,
    onCityChange: (String) -> Unit,
    showUmbrellaAdvice: Boolean,
    onShowUmbrellaAdviceChange: (Boolean) -> Unit,
    temperatureUnit: String,
    onTemperatureUnitChange: (String) -> Unit,
    clothingSensitivity: String,
    onClothingSensitivityChange: (String) -> Unit,
    onRefreshWeather: () -> Unit
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

                Button(
                    onClick = onRefreshWeather,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Refresh weather")
                }
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