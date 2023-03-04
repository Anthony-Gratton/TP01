package ca.qc.cstj.tp01.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deliveries")
data class Delivery(

    @PrimaryKey(autoGenerate = true)
    var idDelivery : Int,

    var ewhyx: Float = 0F,
    var wusnyx:Float = 0F,
    var iaspyx:Float = 0F,
    var smiathil:Float = 0F,
    var vathyx:Float = 0F,
)
