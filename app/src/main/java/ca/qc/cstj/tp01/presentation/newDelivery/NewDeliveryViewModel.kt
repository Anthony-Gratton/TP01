package ca.qc.cstj.tp01.presentation.newDelivery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.tp01.core.AppDatabase
import ca.qc.cstj.tp01.domain.models.Delivery
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.domain.repositories.DeliveryRepository
import ca.qc.cstj.tp01.domain.repositories.TraderRepository
import ca.qc.cstj.tp01.presentation.main.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewDeliveryViewModel(application: Application) : AndroidViewModel(application) {

    private val deliveryRepository = AppDatabase.getInstance(application).DeliveryRepository()
    private val traderRepository = TraderRepository(application)

    private val _newDeliveryUiState = MutableStateFlow<NewDeliveryUiState>(NewDeliveryUiState.Empty)
    val newDeliveryUiState = _newDeliveryUiState.asStateFlow()



    init {
        var trader : Trader

        viewModelScope.launch {
            traderRepository.traderData.collect {
                trader = it
                _newDeliveryUiState.update {
                    return@update NewDeliveryUiState.Loading(trader)
                }
            }
        }
    }

    fun saveNewDelivery(delivery: Delivery, trader: Trader){
            trader.ewhyx -= delivery.ewhyx
            trader.wusnyx -= delivery.wusnyx
            trader.iaspyx -= delivery.iaspyx
            trader.smiathil -= delivery.smiathil
            trader.vathyx -= delivery.vathyx

        viewModelScope.launch {
            saveTrader(trader)
            deliveryRepository.insert(delivery)
            _newDeliveryUiState.update {
                NewDeliveryUiState.Success()
            }
        }

    }


    private fun saveTrader(trader: Trader){
        viewModelScope.launch {
            traderRepository.save(trader)
        }
    }
}