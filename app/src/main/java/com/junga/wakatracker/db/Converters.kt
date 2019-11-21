/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-17
 * @desc
 */

package com.junga.wakatracker.db

import androidx.room.TypeConverter
import java.util.*

class Converters {



        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }
        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }

}