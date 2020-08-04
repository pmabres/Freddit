package co.nz.freddit.data.remote

data class Result<out T>(val status: Status, val data: T?, val message: String) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        NETWORK_ERROR
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, "")
        }

        fun <T> error(message: String, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, message)
        }

        fun <T> networkError(): Result<T> {
            //TODO: Move string to resources file
            return Result(Status.NETWORK_ERROR, null, "Network error")
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, "")
        }
    }
}