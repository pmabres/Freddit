package co.nz.freddit.data.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import co.nz.freddit.data.remote.Result
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    private suspend fun <T> remoteCall(serviceExecutor: suspend () -> Response<T>): Result<T> {
        try {
            val response = serviceExecutor()
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    return Result.success(result)
                }
            }
            return Result.error("No Data")
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            return when (throwable) {
                is IOException -> Result.networkError()
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.response()?.errorBody()?.source()?.let {}
                    Result.error("")
                }
                else -> Result.error("")
            }
        }
    }
    protected fun <T> fetch(localSource: () -> LiveData<T>,
              remoteSource: suspend () -> Response<T>,
              onNetworkCallSuccess: suspend (T?) -> Unit) =
        liveData(Dispatchers.IO) {
            emit(Result.loading())
            val source = localSource.invoke().map { Result.success(it) }
            emitSource(source)
            val networkResult = remoteCall(remoteSource)
            when (networkResult.status) {
                Result.Status.SUCCESS -> onNetworkCallSuccess(networkResult.data) //The data will be updated from the database source.
                else ->  {
                    emit(Result.error(networkResult.message!!))
                    emitSource(source)
                }
            }
        }
}