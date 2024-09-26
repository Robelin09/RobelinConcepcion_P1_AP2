package edu.ucne.robelinconcepcion_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.robelinconcepcion_p1_ap2.data.local.entities.VentaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {
    @Upsert
    suspend fun save(Venta: VentaEntity)
    @Query("""
        SELECT * FROM VENTAS
        WHERE ventaId = :id
        LIMIT 1
    """)
    suspend fun find(id: Int): VentaEntity?

    @Query
        (
        """
                    SELECT * FROM VENTAS
                    WHERE LOWER (:cliente)
                    LIMIT 1
                """
    )
    suspend fun findCliente(cliente: String): VentaEntity?

    @Query
        (
                """
                    SELECT * FROM VENTAS
                    WHERE LOWER (:galon)
                    LIMIT 1
                 """
                )
suspend fun findGalon(galon: Double): VentaEntity?


    @Query
        (
        """
                    SELECT * FROM VENTAS
                    WHERE LOWER (:descuentoGalon)
                    LIMIT 1
                 """
    )
    suspend fun findDescuentoGalon(descuentoGalon: Double): VentaEntity?



    @Query
        (
        """
                    SELECT * FROM VENTAS
                    WHERE LOWER (:precio)
                    LIMIT 1
                 """
    )
    suspend fun findPrecio(precio: Double): VentaEntity?



    @Query
        (
        """
                    SELECT * FROM VENTAS
                    WHERE LOWER (:totalDescuento)
                    LIMIT 1
                 """
    )
    suspend fun findTotalDescuento(totalDescuento: Double): VentaEntity?



    @Query
        (
        """
                    SELECT * FROM VENTAS
                    WHERE LOWER (:total)
                    LIMIT 1
                 """
    )
    suspend fun findTotal(total: Double): VentaEntity?


    @Delete
    suspend fun delete(Venta: VentaEntity)
    @Query("SELECT * FROM VENTAS")
    fun getAll(): Flow<List<VentaEntity>>
}