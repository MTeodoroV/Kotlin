package br.com.organizerlist.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import br.com.organizerlist.R
import br.com.organizerlist.dao.ProductDao
import br.com.organizerlist.model.Product
import java.math.BigInteger
import br.com.organizerlist.databinding.ActivityProductFormBinding

class ProductForm : AppCompatActivity() {

    private val dao = ProductDao()

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Adicionar item"
        setContentView(binding.root)
        configAddProduct()
    }

    private fun configAddProduct() {
        val ButtonAdd = binding.addProduct
        ButtonAdd.setOnClickListener {
            val productNew = createProduct()
            dao.addProduct(productNew)
            finish()
        }
    }

    private fun createProduct(): Product {
        val getFieldName = binding.inputProductName
        val fieldName = getFieldName.text.toString()
        val name = if (fieldName.isBlank()) {
            "Sem nome :) Nice!"
        } else {
            fieldName
        }

        val getFieldQty = binding.inputProductQty
        val fieldQty = getFieldQty.text.toString()
        val qty = if (fieldQty.isBlank()) {
            BigInteger.ONE
        } else {
            BigInteger(fieldQty)
        }

        return Product(
            name = name,
            qty = qty
        )
    }
}