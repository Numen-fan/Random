package com.jiajia.random.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jiajia.random.sunnyweather.logic.Repository
import com.jiajia.random.sunnyweather.logic.model.Place

class PlaceViewModel : ViewModel() {

    // MutableLiveData是一种可变的LiveData，提供setValue\getValue\postValue方法
    // 私有访问，只提供setValue接口，用于监听上层条件的变化
    private val searchLiveData = MutableLiveData<String>()

    // 城市数据的缓存
    val placeList = ArrayList<Place>()

    /*
        实际上 后面的方法体是switchMap的最后一个参数，且是一个单接口匿名类，因此lambda表达式直接将实现拿到方法外了。
        placeLiveData是提供给上层观察的对象，通过装换searchLiveData得到，当searchLiveData发生变化时，就会执行转换
        并将装换结果交给placeLiveData
        map用于提供Ui观察的livedata是在viewModel中创建的
        switchMap的使用场景：提供给Ui观察的liveData是通过调用其它方式得到。
     */
    val placeLiveData = Transformations.switchMap(searchLiveData) {
        query -> Repository.searchPlaces(query) // 返回的liveData每次都不一样，因此这里用switchMap进行一次装换
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}