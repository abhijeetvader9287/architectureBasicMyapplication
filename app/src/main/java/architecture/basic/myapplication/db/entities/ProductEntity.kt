package architecture.basic.myapplication.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductEntity {
    @PrimaryKey
    var id: Int = 0
    var productName: String? = null

    constructor(id: Int, productName: String?) {
        this.id = id
        this.productName = productName
    }
}