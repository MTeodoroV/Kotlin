package br.com.organizerlist.dao

import br.com.organizerlist.model.Product

class ProductDao {

    fun addProduct(product: Product){
        products.add(product)
    }

    fun findAll() : List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}