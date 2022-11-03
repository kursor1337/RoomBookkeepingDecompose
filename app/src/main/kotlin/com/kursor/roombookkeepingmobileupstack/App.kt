package com.kursor.roombookkeepingmobileupstack

import android.app.Application
import android.content.Context
import org.koin.core.Koin
import com.kursor.roombookkeepingmobileupstack.core.ComponentFactory
import com.kursor.roombookkeepingmobileupstack.core.KoinProvider
import com.kursor.roombookkeepingmobileupstack.core.debug_tools.DebugTools
import timber.log.Timber

class App : Application(), KoinProvider {

    override lateinit var koin: Koin
        private set

    override fun onCreate() {
        super.onCreate()
        initLogger()
        koin = createKoin()
        launchDebugTools()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun createKoin(): Koin {
        return Koin().apply {
            loadModules(allModules)
            declare(this@App as Application)
            declare(this@App as Context)
            declare(ComponentFactory(this))
        }
    }

    private fun launchDebugTools() {
        koin.get<DebugTools>().launch()
    }
}