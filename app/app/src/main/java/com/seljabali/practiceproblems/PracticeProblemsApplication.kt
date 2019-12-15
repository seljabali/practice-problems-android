package com.seljabali.practiceproblems

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import java.lang.ref.WeakReference

class PracticeProblemsApplication: Application() {

    companion object {
        lateinit var instance: WeakReference<Context>
    }

    override fun onCreate() {
        super.onCreate()
        instance = WeakReference(applicationContext)
        setupLibraries()
    }

    private fun setupLibraries() {
        Logger.addLogAdapter(AndroidLogAdapter())
        AndroidThreeTen.init(this)
    }
}