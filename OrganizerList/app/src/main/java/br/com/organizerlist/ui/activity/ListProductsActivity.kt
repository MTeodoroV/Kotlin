package br.com.organizerlist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import br.com.organizerlist.R
import br.com.organizerlist.dao.ProductDao
import br.com.organizerlist.ui.recyclerview.adapter.ListProductAdapter

class ListProductsActivity : AppCompatActivity() {

    private val dao = ProductDao()
    private val adapter = ListProductAdapter(context = this, products = dao.findAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Lista de Compras"
        setContentView(R.layout.activity_list_products)
        configRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.findAll())
        configFab()
    }

    private fun configFab() {
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            accessFormProduct()
        }
    }

    private fun accessFormProduct() {
        val intent = Intent(this, ProductForm::class.java)
        startActivity(intent)
    }

    private fun configRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
    }
}