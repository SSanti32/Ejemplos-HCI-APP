package ar.edu.itba.example.api.data.network

class DataSourceException(
    code: Int,
    message: String,
    details: List<String>?
) : Exception(message)