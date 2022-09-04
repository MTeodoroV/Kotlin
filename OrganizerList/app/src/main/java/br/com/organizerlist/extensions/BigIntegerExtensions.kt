package br.com.organizerlist.extensions

import java.math.BigInteger
import java.text.NumberFormat
import java.util.*

fun BigInteger.turnString(): String {
    val formatador: NumberFormat = NumberFormat.getNumberInstance()
    return formatador.format(this)
}