package sa.factory.app1290100.model

import kotlinx.serialization.Serializable

@Serializable
data class WashPackage(
    val id: String,
    val title: String,
    val description: String,
    val priceSar: Double,
    val durationMinutes: Int,
    val iconVector: String = "verified"
)

@Serializable
data class VehicleSpecification(
    val categoryId: String,
    val title: String,
    val pricingMultiplier: Double,
    val description: String
)

@Serializable
data class WashServiceAddon(
    val id: String,
    val title: String,
    val priceSar: Double,
    val extraMinutes: Int
)

@Serializable
data class CarWashBooking(
    val bookingId: String,
    val selectedPackage: WashPackage,
    val vehicle: VehicleSpecification,
    val addons: List<WashServiceAddon> = emptyList(),
    val serviceLocation: String = "الرياض - حي النرجس",
    val vehiclePlate: String = "أ ب ج 1234",
    val totalAmountSar: Double,
    val vatAmountSar: Double,
    val status: BookingStatus = BookingStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class BookingStatus {
    PENDING,
    TECHNICIAN_ASSIGNED,
    EN_ROUTE,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
