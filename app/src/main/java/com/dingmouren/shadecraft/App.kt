package com.dingmouren.shadecraft

import android.app.Application
import android.content.Context
import android.util.Log
import org.opencv.android.OpenCVLoader

class App:Application() {

    companion object{

        private lateinit var instance: App

        fun context():Context{
            return instance.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (!OpenCVLoader.initDebug())
            Log.e("OpenCV", "Unable to load OpenCV!");
        else
            Log.d("OpenCV", "OpenCV loaded Successfully!");
    }
}