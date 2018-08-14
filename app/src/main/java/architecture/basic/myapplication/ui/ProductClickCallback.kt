package architecture.basic.myapplication.ui

import architecture.basic.myapplication.db.entities.ProductEntity

interface ProductClickCallback {
    fun onClick(product: ProductEntity)
}
