package com.jiajia.random.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jiajia.random.R
import com.jiajia.random.sunnyweather.logic.model.Place

/**
 * 搜索地点结果的适配器
 * 注意构造方法中的参数，调用的地方需要传参，同时，Adapter中自动包含当前两个属性
 */
class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>)
    : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_place, parent, false) // 这里和java没有区别
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position] // 这里的列表元素获取方式和js有点像
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }

    /**
     * 注意这里的简写
     */
    override fun getItemCount() = placeList.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }



}