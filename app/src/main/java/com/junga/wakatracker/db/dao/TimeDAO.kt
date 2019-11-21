/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-17
 * @desc
 */

package com.junga.wakatracker.db.dao

import androidx.room.*
import com.junga.wakatracker.db.entity.TimeEntity
import java.util.*


@Dao
interface TimeDAO {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertTimes(times : Array<TimeEntity>)

    @Insert
    fun insertSingleTime(time : TimeEntity) : Long

    @Insert
    fun insertTime(time : TimeEntity) : Long
//
//    @Query("UPDATE time SET time = :time WHERE date = :date")
//    fun updateTime(time : TimeEntity, date : Date);

    @Query("SELECT * FROM time")
    fun getTimes() : List<TimeEntity>

}