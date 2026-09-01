package ua.op.edu.petrov.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.op.edu.petrov.lab4.navigation.TravelPlacesApp
import ua.op.edu.petrov.lab4.ui.theme.PetrovLab4Theme
import ua.op.edu.petrov.lab4.viewmodel.PlacesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val app = application as TravelPlacesApplication
            val placesViewModel: PlacesViewModel = viewModel(
                factory = PlacesViewModel.Factory(app.repository)
            )

            PetrovLab4Theme {
                TravelPlacesApp(viewModel = placesViewModel)
            }
        }
    }
}
