package ua.op.edu.petrov.lab4.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.op.edu.petrov.lab4.ui.AddPlaceScreen
import ua.op.edu.petrov.lab4.ui.PlaceDetailsScreen
import ua.op.edu.petrov.lab4.ui.PlacesListScreen
import ua.op.edu.petrov.lab4.viewmodel.PlacesViewModel

@Composable
fun TravelPlacesApp(viewModel: PlacesViewModel) {
    val navController = rememberNavController()
    val places by viewModel.places.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.PLACES,
    ) {
        composable(AppRoutes.PLACES) {
            PlacesListScreen(
                places = places,
                onPlaceClick = { placeId ->
                    navController.navigate(AppRoutes.details(placeId))
                },
                onAddClick = {
                    navController.navigate(AppRoutes.ADD_PLACE)
                },
            )
        }

        composable(
            route = AppRoutes.DETAILS,
            arguments = listOf(
                navArgument(AppRoutes.PLACE_ID) {
                    type = NavType.LongType
                }
            ),
        ) { backStackEntry ->
            val placeId = backStackEntry.arguments
                ?.getLong(AppRoutes.PLACE_ID)
                ?: -1L

            val placeFlow = remember(placeId) {
                viewModel.observePlace(placeId)
            }
            val place by placeFlow.collectAsStateWithLifecycle(initialValue = null)

            PlaceDetailsScreen(
                place = place,
                onBack = { navController.popBackStack() },
                onToggleVisited = {
                    place?.let(viewModel::toggleVisited)
                },
                onDelete = {
                    place?.let(viewModel::deletePlace)
                    navController.popBackStack()
                },
            )
        }

        composable(AppRoutes.ADD_PLACE) {
            AddPlaceScreen(
                onBack = { navController.popBackStack() },
                onSave = { name, country, description ->
                    if (viewModel.addPlace(name, country, description)) {
                        navController.popBackStack()
                    }
                },
            )
        }
    }
}
