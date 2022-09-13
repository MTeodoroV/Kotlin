package br.com.organizerlist.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.organizerlist.databinding.ActivityProductBinding
import br.com.organizerlist.model.Product

class ListProductAdapter (
    private val context: Context,
    products: List<Product> = emptyList(),
    var clickProduct: (product: Product) -> Unit = {}
    ): RecyclerView.Adapter<ListProductAdapter.ViewHolder>(){

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ActivityProductBinding) :
        RecyclerView.ViewHolder(binding.root){

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    clickProduct(product)
                }
            }
        }

        fun binds(product: Product) {
            this.product = product
            val name = binding.activityProductName
            name.text = product.name
            val qty = binding.activityProductQty
            //val formater:NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            //val coinType: String = formater.formate(product.qty)
            qty.text = "Quantidade: " + product.qty.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ActivityProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
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