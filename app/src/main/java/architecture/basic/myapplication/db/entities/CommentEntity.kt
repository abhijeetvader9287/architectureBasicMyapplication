package architecture.basic.myapplication.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comments", foreignKeys = arrayOf(ForeignKey(entity = ProductEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("productId"), onDelete = ForeignKey.CASCADE)), indices = arrayOf(Index(value = "productId")))
class CommentEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var productId: Int = 0
    var text: String? = null

    constructor(id: Int, productId: Int, text: String?) {
        this.id = id
        this.productId = productId
        this.text = text
    }
}