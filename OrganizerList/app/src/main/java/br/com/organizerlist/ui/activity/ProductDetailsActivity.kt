package br.com.organizerlist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.organizerlist.R
import br.com.organizerlist.database.AppDatabase
import br.com.organizerlist.databinding.ActivityProductDetailsBinding
import br.com.organizerlist.extensions.turnString
import br.com.organizerlist.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private var productId: Long = 0L
    private var product: Product? = null

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    private val productDao by lazy {
        AppDatabase.instancia(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadProduct()
    }

    override fun onResume() {
        super.onResume()
        getProduct()
    }

    private fun getProduct() {
        product = productDao.getId(productId)
        product?.let {
            loadFields(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_details_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId){
                R.id.menu_product_edit -> {
                    Intent(this, ProductForm::class.java).apply {
                        putExtra(KEY_PRODUCT_ID, productId)
                        startActivity(this)
                    }
                }

                R.id.menu_product_remove -> {
                    product?.let { productDao.remove(it) }
                    finish()
                }
            }
        return super.onOptionsItemSelected(item)
    }

    private fun loadProduct() {
        productId = intent.getLongExtra(KEY_PRODUCT_ID, 0L)
    }

    private fun loadFields(productLoaded: Product) {
        with(binding) {
            activityDetailsProductName.text = productLoaded.name
            activityDetailsProductQty.text = productLoaded.qty.turnString()
        }
    }
}