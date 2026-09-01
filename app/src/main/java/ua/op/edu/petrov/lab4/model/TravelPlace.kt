package ua.op.edu.petrov.lab4.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "travel_places")
data class TravelPlace(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val country: String,
    val description: String,
    val visited: Boolean = false,
)
