package com.jiajia.random.sunnyweather.logic

import androidx.lifecycle.liveData
import com.jiajia.random.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

/**
 * 单例类，作为仓库层的统一封装入口
 */
object Repository {

    /**
     * liveData()函数是lifecycle-livedata-ktx库提供的一个非常强大且好用的功能，返回一个LiveData对象
     * Dispatchers.IO 使得该代码块中的代码运行在子线程中
     */
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlace(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        // emit()方法将包装的结果发射出去, 类似于调用LiveData的setValue方法通知数据变化
        emit(result)
    }

}