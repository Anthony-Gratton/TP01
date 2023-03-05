package ca.qc.cstj.tp01.domain.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import ca.qc.cstj.tp01.core.dataStore
import ca.qc.cstj.tp01.domain.models.Trader

import kotlinx.coroutines.flow.map

class TraderRepository(private val context: Context) {

    object TraderKeys {
        val USERNAME = stringPreferencesKey("username")
        val QUANTITY_ELEMENT_EX = floatPreferencesKey("element_EX")
        val QUANTITY_ELEMENT_WU = floatPreferencesKey("element_WU")
        val QUANTITY_ELEMENT_I = floatPreferencesKey("element_I")
        val QUANTITY_ELEMENT_SM = floatPreferencesKey("element_SM")
        val QUANTITY_ELEMENT_VE = floatPreferencesKey("element_VE")
    }

    val traderData = context.dataStore.data.map {
        val name = it[TraderKeys.USERNAME] ?: ""
        val ewhyx = it[TraderKeys.QUANTITY_ELEMENT_EX] ?: 200F
        val wusnyx = it[TraderKeys.QUANTITY_ELEMENT_WU] ?: 200F
        val iaspyx = it[TraderKeys.QUANTITY_ELEMENT_I] ?: 200F
        val smiathil = it[TraderKeys.QUANTITY_ELEMENT_SM] ?: 200F
        val vathyx = it[TraderKeys.QUANTITY_ELEMENT_VE] ?: 200F
        return@map Trader(name, ewhyx,wusnyx,iaspyx,smiathil,vathyx)
    }


    suspend fun save(trader: Trader) {
        context.dataStore.edit {
            it[TraderKeys.USERNAME] = trader.name
            it[TraderKeys.QUANTITY_ELEMENT_EX] = trader.ewhyx
            it[TraderKeys.QUANTITY_ELEMENT_WU] = trader.wusnyx
            it[TraderKeys.QUANTITY_ELEMENT_I] =trader.iaspyx
            it[TraderKeys.QUANTITY_ELEMENT_SM] = trader.smiathil
            it[TraderKeys.QUANTITY_ELEMENT_VE] = trader.vathyx
        }
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit {
            it[TraderKeys.USERNAME] = name
        }
    }

    suspend fun saveElements(element_EX:Float, element_WU:Float, element_I:Float,element_SM:Float,element_VE:Float) {
        context.dataStore.edit {
            it[TraderKeys.QUANTITY_ELEMENT_EX] = element_EX
            it[TraderKeys.QUANTITY_ELEMENT_WU] = element_WU
            it[TraderKeys.QUANTITY_ELEMENT_I] =element_I
            it[TraderKeys.QUANTITY_ELEMENT_SM] = element_SM
            it[TraderKeys.QUANTITY_ELEMENT_VE] = element_VE
        }
    }





}