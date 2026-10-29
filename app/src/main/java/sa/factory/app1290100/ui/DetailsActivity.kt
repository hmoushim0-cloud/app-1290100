package sa.factory.app1290100.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import sa.factory.app1290100.model.WashPackage

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val samplePackage = WashPackage(
            id = "pkg_nano_protect",
            title = "باقة الحماية بتقنية النانو كواتينج السريع",
            description = "غسيل تفصيلي متكامل مع معالجة الهيكل بطبقة مانعة للالتصاق ومقاومة للغبار والأملاح.",
            priceSar = 175.0,
            durationMinutes = 75,
            iconVector = "bolt"
        )
        setContent {
            MaterialTheme(colorScheme = BespokeColorScheme) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CarWashDetailsScreen(
                        washPackage = samplePackage,
                        onBack = { finish() },
                        onProceedCheckout = { finish() }
                    )
                }
            }
        }
    }
}
