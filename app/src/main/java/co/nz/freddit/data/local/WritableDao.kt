package co.nz.freddit.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface WritableDao<T> : ReadableDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: T)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(value: List<T>)
    @Delete
    suspend fun delete(model: T)
}