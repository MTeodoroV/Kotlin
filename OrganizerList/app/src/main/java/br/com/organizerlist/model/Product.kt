package br.com.organizerlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigInteger

@Parcelize
data class Product(
    val name: String,
    val qty: BigInteger
) : Parcelable