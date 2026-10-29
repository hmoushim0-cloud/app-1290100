package sa.factory.app1290100.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import sa.factory.app1290100.model.BookingStatus
import sa.factory.app1290100.model.CarWashBooking
import sa.factory.app1290100.model.VehicleSpecification
import sa.factory.app1290100.model.WashPackage
import sa.factory.app1290100.model.WashServiceAddon

interface CarWashRepository {
    fun getAvailablePackages(): Flow<List<WashPackage>>
    fun getVehicleCategories(): Flow<List<VehicleSpecification>>
    fun getAvailableAddons(): Flow<List<WashServiceAddon>>
    fun getActiveBooking(): Flow<CarWashBooking?>
    suspend fun createBooking(booking: CarWashBooking): Result<String>
}

class InMemoryCarWashRepository : CarWashRepository {
    private val _packages = MutableStateFlow(listOf(
        WashPackage(
            id = "pkg_hydro_clean",
            title = "باقة الغسيل البخاري العميق المتنقل",
            description = "تنظيف خارجي بتقنية ضغط البخار الصديق للبيئة مع إزالة الشحوم وتنظيف الجنوط، وشفط داخلي ومسح المقصورة وتعطير مجاري الهواء.",
            priceSar = 85.0,
            durationMinutes = 45,
            iconVector = "verified"
        ),
        WashPackage(
            id = "pkg_nano_protect",
            title = "باقة الحماية بتقنية النانو كواتينج السريع",
            description = "غسيل تفصيلي متكامل مع معالجة الهيكل بطبقة مانعة للالتصاق ومقاومة للغبار والأملاح، تلميع الإطارات بمركب كربوني، وتطهير فتحات التكييف.",
            priceSar = 175.0,
            durationMinutes = 75,
            iconVector = "bolt"
        ),
        WashPackage(
            id = "pkg_vip_ceramic_detailing",
            title = "باقة التلميع الميداني والنانو سيراميك الفاخرة",
            description = "تصحيح عيوب طلاء المركبة بمرحلتين وإزالة الخدوش الدقيقة، تطبيق طبقة نانو سيراميك عالي الكثافة مع ترطيب الجلد وحماية الزجاج.",
            priceSar = 490.0,
            durationMinutes = 150,
            iconVector = "star"
        )
    ))

    private val _vehicles = MutableStateFlow(listOf(
        VehicleSpecification("veh_sedan", "سيدان مدمجة", 1.0, "سيارات الركاب العادية حتى 5 مقاعد تشمل كامري والنترا"),
        VehicleSpecification("veh_suv_family", "دفع رباعي وعائلية", 1.25, "مركبات الـ SUV المتوسطة والكبيرة مثل برادو ولاندكروزر"),
        VehicleSpecification("veh_luxury_sports", "فارهة ورياضية", 1.45, "مركبات تتطلب عناية حرجة بالطلاء ومواد خالية من الكواشف الحامضية"),
        VehicleSpecification("veh_truck_pickup", "شاحنات وبيك أب", 1.35, "مركبات المهام الشاقة مع تنظيف شامل لحوض الشحن")
    ))

    private val _addons = MutableStateFlow(listOf(
        WashServiceAddon("addon_steam_sterilization", "تعقيم المقصورة بالبخار الحراري الجاف وإزالة الروائح", 65.0, 20),
        WashServiceAddon("addon_nano_wax_sealant", "طبقة نانو واكس سريعة مانعة لالتصاق الأتربة والغبار", 90.0, 25),
        WashServiceAddon("addon_headlight_restoration", "تلميع المصابيح الأمامية وإزالة الاصفرار والأكسدة", 80.0, 30)
    ))

    private val _activeBooking = MutableStateFlow<CarWashBooking?>(null)

    override fun getAvailablePackages(): Flow<List<WashPackage>> = _packages.asStateFlow()
    override fun getVehicleCategories(): Flow<List<VehicleSpecification>> = _vehicles.asStateFlow()
    override fun getAvailableAddons(): Flow<List<WashServiceAddon>> = _addons.asStateFlow()
    override fun getActiveBooking(): Flow<CarWashBooking?> = _activeBooking.asStateFlow()

    override suspend fun createBooking(booking: CarWashBooking): Result<String> {
        _activeBooking.value = booking
        return Result.success(booking.bookingId)
    }
}
