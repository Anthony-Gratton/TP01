package ca.qc.cstj.tp01.presentation.main

import ca.qc.cstj.tp01.domain.models.Trader

sealed class HomeUIState {
    class Success(val trader:Trader) : HomeUIState()
    object DeleteSuccess : HomeUIState()
    object Empty : HomeUIState()
}