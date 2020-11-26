package com.simplx.apps.a30daystohabit.di

import android.content.Context
import androidx.room.Room
import com.simplx.apps.a30daystohabit.room.ApplicationDataBase
import com.simplx.apps.a30daystohabit.room.HabitAppDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ApplicationDataBase {
        return Room.databaseBuilder(
            context,
            ApplicationDataBase::class.java,
            "application_database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun pokemonDao(pokemonDataBase: ApplicationDataBase): HabitAppDao {
        return pokemonDataBase.habitApplicationDao()
    }
}