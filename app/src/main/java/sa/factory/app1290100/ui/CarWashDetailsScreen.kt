package sa.factory.app1290100.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sa.factory.app1290100.model.CarWashBooking
import sa.factory.app1290100.model.VehicleSpecification
import sa.factory.app1290100.model.WashPackage
import sa.factory.app1290100.model.WashServiceAddon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarWashDetailsScreen(
    washPackage: WashPackage?,
    onBack: () -> Unit,
    onProceedCheckout: () -> Unit
) {
    val pkgTitle = washPackage?.title ?: "باقة الحماية بتقنية النانو كواتينج السريع"
    val basePrice = washPackage?.priceSar ?: 175.0
    var selectedSlot by remember { mutableStateOf("04:30 م - 05:45 م") }
    var locationInput by remember { mutableStateOf("الرياض - حي النرجس، شارع أنس بن مالك") }
    var plateInput by remember { mutableStateOf("أ ب ج 5432") }
    val vatAmount = basePrice * 0.15
    val totalAmount = basePrice + vatAmount

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "تفاصيل الحجز والموقع الميداني",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("<", fontWeight = FontWeight.Black, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Vector Graphic Diagnostic / Route (Canvas + Path >= 8 operations)
            BespokeVectorIllustration(
                archetype = "map_radar",
                screenIdx = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            // Package Summary Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = pkgTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = washPackage?.description ?: "غسيل تفصيلي ومعالجة الهيكل بطبقة مانعة للالتصاق ومقاومة للغبار والأملاح.",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )
                    Divider(color = MaterialTheme.colorScheme.surfaceVariant)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("المدة التقديرية للخدمة:", fontSize = 12.sp, color = Color.Gray)
                        Text("${washPackage?.durationMinutes ?: 75} دقيقة", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            // Location & Vehicle Details Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "محددات الخدمة الميدانية:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    OutlinedTextField(
                        value = locationInput,
                        onValueChange = { locationInput = it },
                        label = { Text("موقع وصول الفني المتنقل") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = plateInput,
                        onValueChange = { plateInput = it },
                        label = { Text("بيانات لوحة المركبة ولونها") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Price Breakdown Card
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("قيمة الباقة الأساسية:", fontSize = 13.sp)
                        Text(String.format("%.2f ر.س", basePrice), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("ضريبة القيمة المضافة (15%):", fontSize = 13.sp, color = Color.Gray)
                        Text(String.format("%.2f ر.س", vatAmount), fontSize = 13.sp, color = Color.Gray)
                    }
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("الإجمالي المستحق:", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(
                            String.format("%.2f ر.س", totalAmount),
                            fontWeight = FontWeight.Black,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Button(
                onClick = onProceedCheckout,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "تأكيد الموعد والمتابعة للسداد والتتبع",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}
