package sa.factory.app1290100.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sa.factory.app1290100.data.CarWashRepository
import sa.factory.app1290100.model.BookingStatus
import sa.factory.app1290100.model.CarWashBooking
import sa.factory.app1290100.model.VehicleSpecification
import sa.factory.app1290100.model.WashPackage
import sa.factory.app1290100.model.WashServiceAddon

class CarWashViewModel(
    private val repository: CarWashRepository
) : ViewModel() {
    val packages: StateFlow<List<WashPackage>> = repository.getAvailablePackages()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vehicleCategories: StateFlow<List<VehicleSpecification>> = repository.getVehicleCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val addons: StateFlow<List<WashServiceAddon>> = repository.getAvailableAddons()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeBooking: StateFlow<CarWashBooking?> = repository.getActiveBooking()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val _selectedVehicle = MutableStateFlow<VehicleSpecification?>(null)
    val selectedVehicle: StateFlow<VehicleSpecification?> = _selectedVehicle.asStateFlow()

    private val _selectedPackage = MutableStateFlow<WashPackage?>(null)
    val selectedPackage: StateFlow<WashPackage?> = _selectedPackage.asStateFlow()

    fun selectVehicle(vehicle: VehicleSpecification) {
        _selectedVehicle.value = vehicle
    }

    fun selectPackage(pkg: WashPackage) {
        _selectedPackage.value = pkg
    }

    fun submitBooking(booking: CarWashBooking) {
        viewModelScope.launch {
            repository.createBooking(booking)
        }
    }
}
