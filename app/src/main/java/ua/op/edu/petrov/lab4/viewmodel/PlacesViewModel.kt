package ua.op.edu.petrov.lab4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ua.op.edu.petrov.lab4.data.repository.PlacesRepository
import ua.op.edu.petrov.lab4.model.TravelPlace

class PlacesViewModel(
    private val repository: PlacesRepository,
) : ViewModel() {

    val places: StateFlow<List<TravelPlace>> = repository.places.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun observePlace(id: Long): Flow<TravelPlace?> = repository.observePlace(id)

    fun addPlace(name: String, country: String, description: String): Boolean {
        val cleanName = name.trim()
        val cleanCountry = country.trim()
        val cleanDescription = description.trim()
        if (cleanName.isEmpty() || cleanCountry.isEmpty()) return false

        viewModelScope.launch {
            repository.add(
                TravelPlace(
                    name = cleanName,
                    country = cleanCountry,
                    description = cleanDescription.ifEmpty { "Опис не вказано." },
                )
            )
        }
        return true
    }

    fun toggleVisited(place: TravelPlace) {
        viewModelScope.launch {
            repository.update(place.copy(visited = !place.visited))
        }
    }

    fun deletePlace(place: TravelPlace) {
        viewModelScope.launch {
            repository.delete(place)
        }
    }

    class Factory(
        private val repository: PlacesRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlacesViewModel::class.java)) {
                return PlacesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
