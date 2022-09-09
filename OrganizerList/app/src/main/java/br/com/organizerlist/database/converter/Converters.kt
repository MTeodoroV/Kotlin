package br.com.organizerlist.database.converter

import androidx.room.TypeConverter
import java.math.BigInteger

class Converters {

    @TypeConverter
    fun ofDouble(value: Double?) : BigInteger {
        return value?.let { BigInteger(value.toString()) } ?: BigInteger.ZERO
    }

    @TypeConverter
    fun bigDecimalToDouble(value: BigInteger?) : Double? {
        return value?.let { value.toDouble() }
    }
}