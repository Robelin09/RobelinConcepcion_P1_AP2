package edu.ucne.robelinconcepcion_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ventas")
data class VentaEntity(
    @PrimaryKey
    val ventaId: Int? = null,
    val cliente: String,
    val galon: Double,
    val descuentoGalon: Double,
    val precio: Double,
    val totalDescuento: Double,
    val total: Double
)
