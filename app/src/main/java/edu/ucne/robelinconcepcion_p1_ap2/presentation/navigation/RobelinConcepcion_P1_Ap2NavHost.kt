package edu.ucne.robelinconcepcion_p1_ap2.presentation.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RobelinConcepcion_P1_AP2(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController, startDestination = Screen.ListScreen
    ) {
        composable<Screen.ListScreen> {
            Button(onClick = {
                navHostController.navigate(Screen.RegistroScreen(0))
            }
            ) {
            Text(
                text = "Ir a la segunda pantalla"
            )
            }
        }
        composable<Screen.RegistroScreen>{
            Text(text="Estas en la segunda pantalla")
        }
    }
}

