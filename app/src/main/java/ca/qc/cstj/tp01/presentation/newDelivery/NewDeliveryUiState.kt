package ca.qc.cstj.tp01.presentation.newDelivery


import ca.qc.cstj.tp01.domain.models.Delivery
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.presentation.deliveries.DeliveriesUIState

sealed class NewDeliveryUiState {

    class Success() : NewDeliveryUiState()

    class  Loading(val trader : Trader) : NewDeliveryUiState()

    object Empty : NewDeliveryUiState()
}