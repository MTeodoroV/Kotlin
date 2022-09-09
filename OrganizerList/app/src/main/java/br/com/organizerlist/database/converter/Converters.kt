package br.com.organizerlist.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.math.BigInteger

class Converters {

    @TypeConverter
    fun ofDouble(value: Double?) : BigDecimal {
        return value?.let { BigDecimal(value.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimalToDouble(value: BigDecimal?) : Double? {
        return value?.let { value.toDouble() }
    }
}