package br.com.organizerlist.model

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.organizerlist.R
import java.math.BigInteger

data class Product(
    val name: String,
    val qty: BigInteger
)