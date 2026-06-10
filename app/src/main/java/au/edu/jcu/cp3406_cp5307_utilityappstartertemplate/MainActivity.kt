package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.SettingsScreen
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.UtilityScreen
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.theme.CP3406_CP5603UtilityAppStarterTemplateTheme
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.viewmodel.WeatherViewModel

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
fun UtilityApp(
    weatherViewModel: WeatherViewModel = viewModel()
) {
    var selectedTab by remember { mutableStateOf("Utility") }
    val uiState = weatherViewModel.uiState

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
                    city = uiState.city,
                    temperatureCelsius = uiState.temperatureCelsius,
                    weatherCondition = uiState.weatherCondition,
                    displayedTemperature = weatherViewModel.getDisplayedTemperature(),
                    comfortLabel = weatherViewModel.getComfortLabel(),
                    clothingSensitivity = uiState.clothingSensitivity,
                    outfitRecommendation = weatherViewModel.getOutfitRecommendation()
                )

                "Settings" -> SettingsScreen(
                    city = uiState.city,
                    onCityChange = weatherViewModel::updateCity,
                    showUmbrellaAdvice = uiState.showUmbrellaAdvice,
                    onShowUmbrellaAdviceChange = weatherViewModel::updateUmbrellaAdvice,
                    temperatureUnit = uiState.temperatureUnit,
                    onTemperatureUnitChange = weatherViewModel::updateTemperatureUnit,
                    clothingSensitivity = uiState.clothingSensitivity,
                    onClothingSensitivityChange = weatherViewModel::updateClothingSensitivity
                )
            }
        }
    }
}