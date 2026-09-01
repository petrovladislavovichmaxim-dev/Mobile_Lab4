package ua.op.edu.petrov.lab4.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ua.op.edu.petrov.lab4.model.TravelPlace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesListScreen(
    places: List<TravelPlace>,
    onPlaceClick: (Long) -> Unit,
    onAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Travel Places — ${places.size}")
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = onAddClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Додати місце")
            }

            if (places.isEmpty()) {
                Text("Список порожній. Додайте перше місце — воно буде збережене в Room.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(places, key = { it.id }) { place ->
                        PlaceCard(
                            place = place,
                            onClick = { onPlaceClick(place.id) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaceCard(
    place: TravelPlace,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = place.country,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = if (place.visited) "Статус: відвідано" else "Статус: заплановано",
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = "Натисніть, щоб відкрити деталі",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
