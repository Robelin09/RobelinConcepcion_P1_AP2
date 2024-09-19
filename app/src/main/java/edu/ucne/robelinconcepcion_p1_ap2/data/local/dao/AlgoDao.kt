package edu.ucne.robelinconcepcion_p1_ap2.data.local.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.robelinconcepcion_p1_ap2.data.local.entities.AlgoEntity
import kotlinx.coroutines.flow.Flow

interface AlgoDao {
    @Upsert
    suspend fun save(Algo: AlgoEntity)
    @Query("""
        SELECT * FROM ALGOS
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun find(id: Int): AlgoEntity?
    @Delete
    suspend fun delete(Algo: AlgoEntity)
    @Query("SELECT * FROM ALGOS")
    fun getAll(): Flow<List<AlgoEntity>>
}