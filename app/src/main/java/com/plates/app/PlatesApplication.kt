package com.plates.app

import android.app.Application
import com.plates.app.data.AppDatabase
import com.plates.app.data.PlatesRepository

class PlatesApplication : Application() {

    val repository: PlatesRepository by lazy {
        PlatesRepository(AppDatabase.getInstance(this))
    }
}
