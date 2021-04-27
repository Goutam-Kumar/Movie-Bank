package com.goutam.moviebank.retrofit

import android.util.Log
import retrofit2.Response
import java.lang.Exception

object RetrofitCallProcessor {
    suspend fun <T> processCall(serviceApiCall : suspend () -> Response<T>): RetrofitResult<T>{
        return try {
            val serviceCall = serviceApiCall()
            val responseBody = serviceCall.body()
            if (responseBody != null && serviceCall.isSuccessful){
                RetrofitResult.Success(responseBody)
            }else{
                Log.e("Service Error", serviceCall.raw().request.url.toString())
                Log.e("Service Error code", serviceCall.raw().code.toString())
                RetrofitResult.Error("Something went wrong!")
            }
        }catch (exp: Exception){
            RetrofitResult.Error(exp.localizedMessage ?: "Sorry! Something went wrong!")
        }
    }
}
