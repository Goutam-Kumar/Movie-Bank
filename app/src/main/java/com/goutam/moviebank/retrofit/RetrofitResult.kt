package com.goutam.moviebank.retrofit

sealed class RetrofitResult<out R> {
    data class Success<T>(val data: T): RetrofitResult<T>()
    data class Error(val exception: String): RetrofitResult<Nothing>()
}
