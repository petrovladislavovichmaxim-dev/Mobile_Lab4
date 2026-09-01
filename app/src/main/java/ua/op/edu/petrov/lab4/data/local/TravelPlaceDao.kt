package ua.op.edu.petrov.lab4.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ua.op.edu.petrov.lab4.model.TravelPlace

@Dao
interface TravelPlaceDao {
    @Query("SELECT * FROM travel_places ORDER BY id DESC")
    fun observeAll(): Flow<List<TravelPlace>>

    @Query("SELECT * FROM travel_places WHERE id = :id LIMIT 1")
    fun observeById(id: Long): Flow<TravelPlace?>

    @Insert
    suspend fun insert(place: TravelPlace): Long

    @Update
    suspend fun update(place: TravelPlace)

    @Delete
    suspend fun delete(place: TravelPlace)
}
