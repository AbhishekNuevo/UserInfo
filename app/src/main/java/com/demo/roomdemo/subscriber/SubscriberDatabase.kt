package com.demo.roomdemo.subscriber

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class],version = 1)
abstract class SubscriberDatabase : RoomDatabase() {
    abstract fun subscriberDao() : SubscriberDao

    companion object {
        private var instance : SubscriberDatabase ? = null
        fun getDataBaseInstance(context: Context) : SubscriberDatabase {
          synchronized(this)  {
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_database").build()

                }

                return instance as SubscriberDatabase
            }

        }
    }
}