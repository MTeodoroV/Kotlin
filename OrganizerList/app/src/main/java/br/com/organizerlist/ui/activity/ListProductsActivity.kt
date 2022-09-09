package br.com.organizerlist.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.organizerlist.dao.ProductDao
import br.com.organizerlist.database.AppDatabase
import br.com.organizerlist.databinding.ActivityListProductsBinding
import br.com.organizerlist.model.Product
import br.com.organizerlist.ui.recyclerview.adapter.ListProductAdapter
import java.math.BigInteger


class ListProductsActivity : AppCompatActivity() {

    private val dao = ProductDao()
    private val adapter = ListProductAdapter(context = this, products = dao.findAll())
    private val binding by lazy {
        ActivityListProductsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Lista de Compras"
        setContentView(binding.root)
        configRecyclerView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instancia(this)
        val productDao = db.productDao()
        adapter.update(productDao.listAll())
    }

    private fun configFab() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            accessFormProduct()
        }
    }

    private fun accessFormProduct() {
        val intent = Intent(this, ProductForm::class.java)
        startActivity(intent)
    }

    private fun configRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        adapter.clickProduct = {
            val intent = Intent(
                this,
                ProductDetailsActivity::class.java
            ).apply {
                putExtra(KEY_PRODUCT, it)
            }
            startActivity(intent)
        }
    }
}