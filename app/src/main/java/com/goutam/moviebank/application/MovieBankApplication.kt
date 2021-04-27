package com.goutam.moviebank.application

import android.app.Application

class MovieBankApplication: Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: MovieBankApplication
    }
}
