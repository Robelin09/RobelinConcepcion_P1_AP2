package edu.ucne.robelinconcepcion_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.robelinconcepcion_p1_ap2.presentation.venta.VentaDetailsScreen
import edu.ucne.robelinconcepcion_p1_ap2.presentation.venta.VentaListScreen
import edu.ucne.robelinconcepcion_p1_ap2.presentation.venta.VentaScreen

@Composable
fun RobelinConcepcion_P1_AP2NavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.VentaList
    ) {
        composable<Screen.VentaList> {
            VentaListScreen(
                createVenta = {
                    navHostController.navigate(Screen.Venta(0))
                },
                onVentaClick = {
                    navHostController.navigate(Screen.VentaDetails(it))
                }
            )
        }
        composable<Screen.Venta> {
            val args = it.toRoute<Screen.Venta>()
            VentaScreen(
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.VentaDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.VentaDetails>()
            VentaDetailsScreen(
                ventaId = args.ventaId,
                goBack = { navHostController.navigateUp() }
            )

        }
    }
}
