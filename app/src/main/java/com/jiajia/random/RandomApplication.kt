package com.jiajia.random

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class RandomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN = "BorPZ2KBXxVbGzqj" // 当前是测试token，还没有申请到个人的token
    }
}