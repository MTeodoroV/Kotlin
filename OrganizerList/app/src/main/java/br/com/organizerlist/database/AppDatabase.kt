package br.com.organizerlist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.organizerlist.database.converter.Converters
import br.com.organizerlist.database.dao.ProductDao
import br.com.organizerlist.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}