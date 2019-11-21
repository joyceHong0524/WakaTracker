/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-17
 * @desc
 */

package com.junga.wakatracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.junga.wakatracker.db.dao.TimeDAO
import com.junga.wakatracker.db.entity.TimeEntity

@Database(entities = [TimeEntity :: class],version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase(){

    abstract fun getTimeDao() : TimeDAO
    companion object{
        private var INSTANCE: MyDatabase?= null
        fun getInstance(context : Context) : MyDatabase? {
            if(INSTANCE == null){
                synchronized(MyDatabase ::class){
                    INSTANCE = Room.databaseBuilder(context, MyDatabase :: class.java,"time").build()
                }
            }
        return INSTANCE
        }

    }
}