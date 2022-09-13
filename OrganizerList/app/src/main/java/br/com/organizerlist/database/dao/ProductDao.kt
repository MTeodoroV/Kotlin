package br.com.organizerlist.database.dao

import androidx.room.*
import br.com.organizerlist.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun listAll() : List<Product>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getId(id:Long) : Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg product: Product)

    //@Update
    //fun update(product: Product)

    @Delete
    fun remove(product: Product)
}