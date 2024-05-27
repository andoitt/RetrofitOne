package com.example.retrofitone.core.data


import retrofit2.Call
import java.lang.IllegalStateException
import java.net.UnknownHostException

interface CloudDataSource {

    fun data(): CloudResponse

    class Base(
        private val service: UserInfoService,
    ) : CloudDataSource {
        override fun data(): CloudResponse {
            try {
                val data: Call<CloudResponse> = service.data()
                return data.execute().body()!!
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    throw IllegalStateException("Internet connection problems")
                else
                    throw IllegalStateException("unknown error")
            }
        }
    }
}

/*    fun data(): List<String>

    class Base(
        private val service: UserInfoService,
    ) : CloudDataSource {
        override fun data(): List<String> {
            try {
                val response: Call<List<String>> = service.data()
                return response.execute().body()!!.items
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    throw IllegalStateException("No internet connection")
                else
                    throw IllegalStateException("unkown error")
            }
        }
    }
}*/

/*
data class List<String>(
    @SerializedName("results")
    val items: List<String>
)*/
