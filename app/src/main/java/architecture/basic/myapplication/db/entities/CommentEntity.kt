package architecture.basic.myapplication.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comments", foreignKeys = arrayOf(ForeignKey(entity = ProductEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("productid"), onDelete = ForeignKey.CASCADE)), indices = arrayOf(Index(value = "productid")))
class CommentEntity {
    @PrimaryKey
    var id: Int = 0
    var productid: Int = 0
    var text: String? = null

    constructor(id: Int, productid: Int, text: String?) {
        this.id = id
        this.productid = productid
        this.text = text
    }
}