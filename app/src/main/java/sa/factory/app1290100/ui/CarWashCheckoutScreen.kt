package sa.factory.app1290100.ui

import androidx.compose.foundation.background
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarWashCheckoutScreen(
    onBack: () -> Unit,
    onComplete: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "حالة الفني المتنقل والفاتورة",
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
            // Vector Graphic Gauge / Tracking (Canvas + Path >= 8 operations)
            BespokeVectorIllustration(
                archetype = "booking_timeline",
                screenIdx = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            // Live Mobile Wash Van Tracking Stepper Card
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "حالة الطلب: الفني في الطريق إليك (وصول خلال 12 دقيقة)",
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "فريق الغسيل المتنقل: كابتن أحمد - شاحنة غسيل هيدروليكية رقم 4",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "رمز التأكيد الأمني: 4892 (يتم تسليمه للفني عند البدء)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            // Stepper Status Flow
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("مراحل تنفيذ الخدمة الميدانية:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("[1] تم تأكيد الحجز وجدولة الموعد", fontSize = 12.sp, color = Color(0xFF10B981))
                    Text("[2] شاحنة الغسيل المتنقلة متجهة لموقعك (حي النرجس)", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Text("[3] بدء الغسيل البخاري وتطبيق معالجة النانو كواتينج", fontSize = 12.sp, color = Color.Gray)
                    Text("[4] فحص الجودة الختامي واعتماد الفاتورة الإلكترونية", fontSize = 12.sp, color = Color.Gray)
                }
            }

            // ZATCA VAT Invoice Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("الفاتورة الضريبية المبسطة (متوافقة مع هيئة الزكاة):", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("الرقم المرجعي: INV-1290100-2026", fontSize = 11.sp, color = Color.Gray)
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("باقة الحماية بتقنية النانو كواتينج:", fontSize = 12.sp)
                        Text("175.00 ر.س", fontSize = 12.sp)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("ضريبة القيمة المضافة (15%):", fontSize = 12.sp, color = Color.Gray)
                        Text("26.25 ر.س", fontSize = 12.sp, color = Color.Gray)
                    }
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("الإجمالي المدفوع عبر مدى / Apple Pay:", fontWeight = FontWeight.Black, fontSize = 14.sp)
                        Text("201.25 ر.س", fontWeight = FontWeight.Black, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            Button(
                onClick = onComplete,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("العودة إلى الشاشة الرئيسية", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
        }
    }
}
