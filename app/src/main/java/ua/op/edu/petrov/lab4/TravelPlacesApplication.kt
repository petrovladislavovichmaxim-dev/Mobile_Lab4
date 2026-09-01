package ua.op.edu.petrov.lab4

import android.app.Application
import ua.op.edu.petrov.lab4.data.local.TravelPlacesDatabase
import ua.op.edu.petrov.lab4.data.repository.PlacesRepository

class TravelPlacesApplication : Application() {
    val database by lazy { TravelPlacesDatabase.getDatabase(this) }
    val repository by lazy { PlacesRepository(database.travelPlaceDao()) }
}
