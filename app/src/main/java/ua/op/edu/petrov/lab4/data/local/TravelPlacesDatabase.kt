package ua.op.edu.petrov.lab4.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.op.edu.petrov.lab4.model.TravelPlace

@Database(
    entities = [TravelPlace::class],
    version = 1,
    exportSchema = false,
)
abstract class TravelPlacesDatabase : RoomDatabase() {
    abstract fun travelPlaceDao(): TravelPlaceDao

    companion object {
        @Volatile
        private var INSTANCE: TravelPlacesDatabase? = null

        fun getDatabase(context: Context): TravelPlacesDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TravelPlacesDatabase::class.java,
                    "travel_places.db",
                ).build().also { INSTANCE = it }
            }
    }
}
