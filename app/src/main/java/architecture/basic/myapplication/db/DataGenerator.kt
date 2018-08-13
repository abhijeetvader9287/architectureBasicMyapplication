package architecture.basic.myapplication.db

import architecture.basic.myapplication.db.entities.CommentEntity
import architecture.basic.myapplication.db.entities.ProductEntity
import java.util.*

object DataGenerator {
    private val FIRST = arrayOf("Special edition", "New", "Cheap", "Quality", "Used")
    private val SECOND = arrayOf("Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle")
    private val DESCRIPTION = arrayOf("is finally here", "is recommended by Stan S. Stanman", "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine")
    private val COMMENTS = arrayOf("Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6")
    fun generateProducts(): List<ProductEntity> {
        val products = ArrayList<ProductEntity>(FIRST.size * SECOND.size)
        val rnd = Random()
        for (i in FIRST.indices) {
            for (j in SECOND.indices) {
                val product = ProductEntity(FIRST.size * i + j + 1, FIRST[i] + " " + SECOND[j])

                products.add(product)
            }
        }
        return products
    }

    fun generateCommentsForProducts(
            products: List<ProductEntity>): List<CommentEntity> {
        val comments = ArrayList<CommentEntity>()
        val rnd = Random()

        for (product in products) {
            val commentsNumber = rnd.nextInt(5) + 1
            for (i in 0 until commentsNumber) {
                val comment = CommentEntity(i, product.id, COMMENTS[i] + " for " + product.productName)
                comments.add(comment)
            }
        }

        return comments
    }
}
