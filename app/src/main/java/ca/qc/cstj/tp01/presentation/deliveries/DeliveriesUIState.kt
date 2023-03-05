package ca.qc.cstj.tp01.presentation.deliveries

import ca.qc.cstj.tp01.domain.models.Delivery
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.presentation.main.HomeUIState

sealed class DeliveriesUIState {
    class Success(val deliveries: List<Delivery>, val trader : Trader) : DeliveriesUIState()

    object Empty : DeliveriesUIState()
}