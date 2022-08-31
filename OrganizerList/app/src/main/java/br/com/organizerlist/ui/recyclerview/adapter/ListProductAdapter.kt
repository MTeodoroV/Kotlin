package br.com.organizerlist.ui.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.organizerlist.R
import br.com.organizerlist.model.Product
import java.text.NumberFormat

class ListProductAdapter (
    private val context: Context,
    products: List<Product>
    ): RecyclerView.Adapter<ListProductAdapter.ViewHolder>(){

    private val products = products.toMutableList()

    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        fun binds(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.activity_product_name)
            name.text = product.name
            val qty = itemView.findViewById<TextView>(R.id.activity_product_qty)
            //val formater:NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            //val coinType: String = formater.formate(product.qty)
            qty.text = "Quantidade: " + product.qty.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val product = products[position]
        holder.binds(product)
    }

    override fun getItemCount(): Int = products.size;
    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}