package ca.qc.cstj.tp01.domain.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ca.qc.cstj.tp01.domain.models.Delivery
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryRepository {

//    fun retrieveAll() : List<Note> {
//        //TODO: Genérer les notes
//        var number = 0
//        val notes =  generateSequence {
//            (Note("Note ${++number}", "Contenu $number", Constants.COLORS.random()))
//                .takeIf { number <= Constants.NUMBER_OF_NOTES }
//        }
//
//        return notes.toList()
//    }

    @Query("SELECT * FROM deliveries")
    fun retrieveAll() : Flow<List<Delivery>>

    @Insert
    suspend fun insert(note:Delivery)

    @Delete
    suspend fun delete(note: Delivery)

    @Query("DELETE FROM deliveries")
    fun deleteAll()

}
