package ca.qc.cstj.tp01.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.qc.cstj.tp01.core.AppDatabase
import ca.qc.cstj.tp01.domain.models.Delivery
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.domain.repositories.TraderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application)  {
    private val traderRepository = TraderRepository(application)

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Empty)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        var trader = Trader()

        viewModelScope.launch {
            traderRepository.TraderData.collect {
                trader = it
                _mainUiState.update {
                    return@update MainUiState.Success(trader)
                }
            }
        }
    }
}