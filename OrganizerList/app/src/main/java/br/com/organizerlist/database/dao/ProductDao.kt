package br.com.organizerlist.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.organizerlist.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun listAll() : List<Product>

    @Insert
    fun insert(vararg product: Product)
}