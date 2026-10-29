package sa.factory.app1290100.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import sa.factory.app1290100.data.InMemoryCarWashRepository
import sa.factory.app1290100.model.WashPackage

val BespokeColorScheme = darkColorScheme(
    primary = Color(0xFF0284C7),
    secondary = Color(0xFFF59E0B),
    tertiary = Color(0xFF06B6D4),
    background = Color(0xFF0F172A),
    surface = Color(0xFF1E293B),
    surfaceVariant = Color(0xFF334155),
    primaryContainer = Color(0xFF0369A1),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC),
    onPrimaryContainer = Color.White
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = InMemoryCarWashRepository()
        val viewModel = CarWashViewModel(repository)

        // Mathematical Lock 2: Support start_screen and screen intent extras
        val startScreen = intent?.getStringExtra("start_screen")
            ?: intent?.getStringExtra("screen")
            ?: "home"

        setContent {
            MaterialTheme(colorScheme = BespokeColorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf(startScreen) }
                    var selectedPkg by remember { mutableStateOf<WashPackage?>(null) }

                    when (currentScreen) {
                        "details" -> {
                            CarWashDetailsScreen(
                                washPackage = selectedPkg,
                                onBack = { currentScreen = "home" },
                                onProceedCheckout = { currentScreen = "checkout" }
                            )
                        }
                        "checkout", "tracking" -> {
                            CarWashCheckoutScreen(
                                onBack = { currentScreen = "details" },
                                onComplete = { currentScreen = "home" }
                            )
                        }
                        else -> {
                            CarWashHomeScreen(
                                viewModel = viewModel,
                                onSelectPackage = { pkg ->
                                    selectedPkg = pkg
                                    currentScreen = "details"
                                },
                                onQuickCheckout = {
                                    currentScreen = "checkout"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
