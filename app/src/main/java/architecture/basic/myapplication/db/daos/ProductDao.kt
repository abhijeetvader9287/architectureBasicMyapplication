package architecture.basic.myapplication.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import architecture.basic.myapplication.db.entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun loadAllProducts(): LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductEntity>)

    @Query("select * from products where id = :productId")
    fun loadProduct(productId: Int): LiveData<ProductEntity>

    @Query("select * from products where id = :productId")
    fun loadProductSync(productId: Int): ProductEntity
}
