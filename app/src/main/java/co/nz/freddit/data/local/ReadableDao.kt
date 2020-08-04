package co.nz.freddit.data.local

import androidx.lifecycle.LiveData

interface ReadableDao<T> {
    fun get(id: Long): LiveData<T>
    fun getAll() : LiveData<List<T>>
}