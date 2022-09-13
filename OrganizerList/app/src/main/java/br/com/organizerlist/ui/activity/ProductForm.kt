package br.com.organizerlist.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.organizerlist.database.AppDatabase
import br.com.organizerlist.database.dao.ProductDao
import br.com.organizerlist.databinding.ActivityProductFormBinding
import br.com.organizerlist.model.Product
import java.math.BigDecimal

class ProductForm : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private var productId = 0L
    private val productDao:ProductDao by lazy {
        val db = AppDatabase.instancia(this)
        db.productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Adicionar item"
        setContentView(binding.root)
        configAddProduct()
        tryLoadProduct()
    }

    private fun tryLoadProduct() {
        productId = intent.getLongExtra(KEY_PRODUCT_ID, 0L)
    }

    override fun onResume() {
        super.onResume()
        productDao.getId(productId)?.let {
            fillFieldsProduct(it)
        }
    }

    private fun fillFieldsProduct(product: Product) {
        title = "Alterar produto"
        binding.inputProductName.setText(product.name)
        binding.inputProductQty.setText(product.qty.toPlainString())
    }

    private fun configAddProduct() {
        val ButtonAdd = binding.addProduct
        val db = AppDatabase.instancia(this)
        val productDao = db.productDao()
        ButtonAdd.setOnClickListener {
            val productNew = createProduct()
            productDao.insert(productNew)
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
            id = productId,
            name = name,
            qty = qty
        )
    }
}