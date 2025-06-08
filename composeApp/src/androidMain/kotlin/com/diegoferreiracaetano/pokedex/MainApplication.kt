package com.diegoferreiracaetano.pokedex

import android.app.Application
import com.diegoferreiracaetano.pokedex.di.initKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}