package com.example.retrofitone.core.data

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import java.net.UnknownHostException


interface UserInfoService {

    @GET("api/")
    fun data(): Call<CloudResponse>
}

class MockService(
    private val currentIndex: IntCache
) : UserInfoService {

    private val mockItems = listOf(
        CloudResponse(
            listOf(
                Result(
                    gender = "female",
                   location = Location(city =  "Portsmouth"),
                )
            )
        ),
        CloudResponse(
            listOf(
                Result(
                    gender = "male",
                    location = Location(city =  "Norwich"),
                )
            )
        )
    )

    private var showSuccess = false
    override fun data(): Call<CloudResponse> {
        Thread.sleep(2000)
        if (showSuccess) {
            showSuccess = false
            return object : Call<CloudResponse> {
                override fun clone(): Call<CloudResponse> = this


                override fun execute(): Response<CloudResponse> {
                    var newIndex = currentIndex.read()
                    if (newIndex == 2) newIndex = 0
                    currentIndex.save(newIndex + 1)
                    return Response.success(mockItems[newIndex])
                }

                override fun isExecuted(): Boolean {
                    return false
                }

                override fun cancel() {
                    TODO("Not yet implemented")
                }

                override fun isCanceled(): Boolean {
                    return false
                }

                override fun request(): Request {
                    TODO("Not yet implemented")
                }

                override fun timeout(): Timeout {
                    TODO("Not yet implemented")
                }

                override fun enqueue(callback: Callback<CloudResponse>) {
                    TODO("Not yet implemented")
                }
            }
        } else {
            showSuccess = true
            throw UnknownHostException()
        }
    }
}