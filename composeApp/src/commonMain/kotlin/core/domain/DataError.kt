package core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        BAD_REQUEST, // 400
        UNAUTHORIZED, // 401
        PAYMENT_REQUIRED, // 402
        NOT_FOUND, // 404
        REQUEST_TIMEOUT, //408
        TOO_MANY_REQUESTS, // 429
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}