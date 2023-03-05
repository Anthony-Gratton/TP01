package ca.qc.cstj.tp01.presentation.deliveries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.tp01.core.AppDatabase
import ca.qc.cstj.tp01.domain.models.Delivery
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.domain.repositories.TraderRepository
import ca.qc.cstj.tp01.presentation.main.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeliveriesViewModel(application: Application) : AndroidViewModel(application) {

    private val deliveriesRepository = AppDatabase.getInstance(application).DeliveryRepository()
    private val traderRepository = TraderRepository(application)
    private val _deliveriesUiState = MutableStateFlow<DeliveriesUIState>(DeliveriesUIState.Empty)
    val deliveriesUiState = _deliveriesUiState.asStateFlow()

    init {
        var deliveries: List<Delivery> = listOf()
        var trader = Trader()

        viewModelScope.launch {
            traderRepository.traderData.collect {
                trader = it
                _deliveriesUiState.update {
                    return@update DeliveriesUIState.Success(deliveries, trader)
                }
            }
        }

        //1er Thread démarré
        viewModelScope.launch {
            deliveriesRepository.retrieveAll().collect {  //notes = kayak
                deliveries = it
                _deliveriesUiState.update {
                    return@update DeliveriesUIState.Success(deliveries, trader)
                }
            }
        }

    }

}