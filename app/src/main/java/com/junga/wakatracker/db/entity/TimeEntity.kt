/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-17
 * @desc
 */

package com.junga.wakatracker.db.entity

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*


@Entity(tableName = "time")
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id : Long?,
    var date : Date,
    var time : Int = 0
)