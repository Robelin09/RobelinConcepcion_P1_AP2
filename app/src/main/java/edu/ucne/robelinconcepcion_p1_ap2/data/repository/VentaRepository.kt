package edu.ucne.robelinconcepcion_p1_ap2.data.repository

import edu.ucne.robelinconcepcion_p1_ap2.data.local.dao.VentaDao
import edu.ucne.robelinconcepcion_p1_ap2.data.local.entities.VentaEntity
import javax.inject.Inject

class VentaRepository @Inject constructor(
    private val ventaDao: VentaDao
) {
    suspend fun save(algo: VentaEntity) = ventaDao.save(algo)
    suspend fun find(id: Int) = ventaDao.find(id)
    suspend fun getDescripcion(cliente: String) = ventaDao.findCliente(cliente)

    suspend fun delete(algo: VentaEntity) = ventaDao.delete(algo)
    fun getVentas() = ventaDao.getAll()
}
