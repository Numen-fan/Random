package com.jiajia.random.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 单例类
 */
object SunnyWeatherNetwork {

    // 创建PlaceService接口动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()

    /**
     *  当调用此方法时，Retrofit就会立即响应网络请求，并且将当前协程阻塞，等待server响应之后，
     *  await()函数会将解析出来的数据模型对象取出并返回，同时恢复当前协程的执行。
     *  得到await()函数的返回值后会将该数据再返回到上一层。
     */
    suspend fun searchPlace(query: String) = placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }

}