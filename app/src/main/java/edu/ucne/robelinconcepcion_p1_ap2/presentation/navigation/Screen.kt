package edu.ucne.robelinconcepcion_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object VentaList : Screen()

    @Serializable
    data class Venta(val VentaId: Int) : Screen()

    @Serializable
    data class VentaDetails (val ventaId: Int) : Screen()

}