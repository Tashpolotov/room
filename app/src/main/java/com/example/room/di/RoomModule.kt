package com.example.room.di

import android.content.Context
import androidx.room.Room
import com.example.room.data.AppDatabase
import com.example.room.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "Todo")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun userDao(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao()

}