package com.simplx.apps.a30daystohabit.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplx.apps.a30daystohabit.model.HabitTracerViewModel

class FactoryViewModel(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HabitTracerViewModel(application) as T
    }
}