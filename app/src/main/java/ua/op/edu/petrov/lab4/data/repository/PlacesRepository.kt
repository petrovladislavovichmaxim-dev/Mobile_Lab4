package ua.op.edu.petrov.lab4.data.repository

import kotlinx.coroutines.flow.Flow
import ua.op.edu.petrov.lab4.data.local.TravelPlaceDao
import ua.op.edu.petrov.lab4.model.TravelPlace

class PlacesRepository(
    private val dao: TravelPlaceDao,
) {
    val places: Flow<List<TravelPlace>> = dao.observeAll()

    fun observePlace(id: Long): Flow<TravelPlace?> = dao.observeById(id)

    suspend fun add(place: TravelPlace): Long = dao.insert(place)

    suspend fun update(place: TravelPlace) = dao.update(place)

    suspend fun delete(place: TravelPlace) = dao.delete(place)
}
