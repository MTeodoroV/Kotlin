package br.com.organizerlist.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import br.com.organizerlist.R
import br.com.organizerlist.dao.ProductDao
import br.com.organizerlist.model.Product
import java.math.BigInteger

class ProductForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)

        val ButtonAdd = findViewById<Button>(R.id.addProduct)
        ButtonAdd.setOnClickListener{
            val fieldName = findViewById<EditText>(R.id.inputProductName)
            val name = fieldName.text.toString()
            val getFieldQty = findViewById<EditText>(R.id.inputProductQty)
            val fieldQty = getFieldQty.text.toString()
            val qty = if(fieldQty.isBlank()) {
                BigInteger.ZERO
            } else {
                BigInteger(fieldQty)
            }


            val productNew = Product(
                name = name,
                qty = qty
            )

            val dao = ProductDao()
                dao.addProduct(productNew)
            finish()
        }
    }
}