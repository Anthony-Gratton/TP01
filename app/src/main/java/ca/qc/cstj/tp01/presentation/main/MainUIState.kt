package ca.qc.cstj.tp01.presentation.main

import ca.qc.cstj.tp01.domain.models.Trader

sealed class MainUiState {
    class Success(val trader:Trader) : MainUiState()

    object Empty : MainUiState()
}