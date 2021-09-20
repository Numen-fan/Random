package com.jiajia.random.sunnyweather.ui.place

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiajia.random.R
import kotlinx.android.synthetic.main.fragment_weather_place.*

class PlaceFragment : Fragment() {

    // 使用lazy懒加载技术来获取PlaceViewModel的实例，提供全局可用的情况下，不需要关心它的初始化时机
    private val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_place, container, false)
    }

    // 生命周期，onCreateView->onActivityCreated
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerviewPlace.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerviewPlace.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initListener() {
        edtSearchPlace.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                recyclerviewPlace.visibility = View.GONE
                bgImage.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val places = result.getOrNull()
            if (places != null) {
                recyclerviewPlace.visibility = View.VISIBLE
                bgImage.visibility = View.GONE
                viewModel.placeList.clear() // 这里清除缓存，并重新赋值，不太合理啊
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace() // 将底层的异常在这里打印
            }
        })
    }

    companion object {

        private const val TAG = "PlaceFragment"



    }
}