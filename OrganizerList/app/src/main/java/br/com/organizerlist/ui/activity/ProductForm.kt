package br.com.organizerlist.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.organizerlist.dao.ProductDao
import br.com.organizerlist.database.AppDatabase
import br.com.organizerlist.databinding.ActivityProductFormBinding
import br.com.organizerlist.model.Product
import java.math.BigInteger
import java.math.BigDecimal

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
        val db = AppDatabase.instancia(this)
        val productDao = db.productDao()
        ButtonAdd.setOnClickListener {
            val productNew = createProduct()
            productDao.insert(
                productNew
            )
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
            BigDecimal.ONE
        } else {
            BigDecimal(fieldQty)
        }

        return Product(
            name = name,
            qty = qty
        )
    }
}