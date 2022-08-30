package br.com.organizerlist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.util.Log
import br.com.organizerlist.dao.ProductDao
import br.com.organizerlist.ui.activity.ProductForm
import br.com.organizerlist.ui.recyclerview.adapter.ListProductAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dao = ProductDao()
        Log.i("MainActivity", "onCreate: ${dao.findAll()}")
        recyclerView.adapter = ListProductAdapter(context = this, products = dao.findAll())
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener{
            val intent = Intent(this, ProductForm::class.java)
            startActivity(intent)
        }
    }
}