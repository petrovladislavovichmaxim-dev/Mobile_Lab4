package ua.op.edu.petrov.lab4.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
fun PlaceDetailsScreen(
    place: TravelPlace?,
    onBack: () -> Unit,
    onToggleVisited: () -> Unit,
    onDelete: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Деталі місця") })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Назад")
            }

            if (place == null) {
                Text("Завантаження даних або місце з переданим id не знайдено.")
            } else {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = place.country,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "ID з маршруту: ${place.id}",
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = place.description,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = if (place.visited) "Місце відвідано" else "Місце ще не відвідано",
                    style = MaterialTheme.typography.bodyMedium,
                )

                Button(
                    onClick = onToggleVisited,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(if (place.visited) "Позначити як заплановане" else "Позначити як відвідане")
                }

                OutlinedButton(
                    onClick = onDelete,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Видалити")
                }
            }
        }
    }
}
