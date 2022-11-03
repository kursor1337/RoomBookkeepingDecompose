package com.kursor.roombookkeepingmobileupstack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.kursor.roombookkeepingmobileupstack.core.ComponentFactory
import com.kursor.roombookkeepingmobileupstack.core.activity.ActivityProvider
import com.kursor.roombookkeepingmobileupstack.core.koin
import com.kursor.roombookkeepingmobileupstack.core.theme.AppTheme
import com.kursor.roombookkeepingmobileupstack.features.root.createRootComponent
import com.kursor.roombookkeepingmobileupstack.features.root.ui.RootUi

// Note: rootComponent survives configuration changes due to "android:configChanges" setting in the manifest.
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val activityProvider = application.koin.get<ActivityProvider>()
        activityProvider.attachActivity(this)
        lifecycle.asEssentyLifecycle().doOnDestroy {
            activityProvider.detachActivity()
        }

        val componentFactory = application.koin.get<ComponentFactory>()
        val rootComponent = componentFactory.createRootComponent(defaultComponentContext())

        setContent {
            AppTheme {
                RootUi(rootComponent)
            }
        }
    }
}