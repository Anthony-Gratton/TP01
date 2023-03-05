package ca.qc.cstj.tp01.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.domain.repositories.TraderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.tp01.core.AppDatabase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(application: Application) : AndroidViewModel(application)  {
    private val traderRepository = TraderRepository(application)
    private val deliveriesRepository = AppDatabase.getInstance(application).DeliveryRepository()

    private val _mainUiState = MutableStateFlow<HomeUIState>(HomeUIState.Empty)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        var trader: Trader

        viewModelScope.launch {
            traderRepository.traderData.collect {
                trader = it
                _mainUiState.update {
                    return@update HomeUIState.Success(trader)
                }
            }
        }
    }

    fun saveName(name: String) {
        viewModelScope.launch {
            traderRepository.saveName(name)
        }
    }

    private fun saveTrader(trader: Trader){
        viewModelScope.launch {
            traderRepository.save(trader)
        }
    }

    fun rechargeElements(trader: Trader){
        viewModelScope.launch {
                trader.ewhyx += ((Random.nextFloat() * 150) + 50)
                trader.wusnyx += ((Random.nextFloat() * 150) + 50)
                trader.iaspyx += ((Random.nextFloat() * 150) + 50)
                trader.smiathil += ((Random.nextFloat() * 150) + 50)
                trader.vathyx += ((Random.nextFloat() * 150) + 50)
                saveTrader(trader)
        }
    }

     fun upload(){
        viewModelScope.launch{
            deliveriesRepository.deleteAll()
            _mainUiState.update {
                return@update HomeUIState.DeleteSuccess
            }
        }

    }


}