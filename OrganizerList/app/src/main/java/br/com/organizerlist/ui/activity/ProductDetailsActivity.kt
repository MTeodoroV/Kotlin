package br.com.organizerlist.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.organizerlist.databinding.ActivityProductDetailsBinding
import br.com.organizerlist.extensions.turnString
import br.com.organizerlist.model.Product

class ProductDetailsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadProduct()
    }

    private fun loadProduct() {
        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let { productLoaded ->
            loadFields(productLoaded)
        } ?: finish()
    }

    private fun loadFields(productLoaded: Product) {
        with(binding) {
            activityDetailsProductName.text = productLoaded.name
            activityDetailsProductQty.text = productLoaded.qty?.turnString()
        }
    }
}