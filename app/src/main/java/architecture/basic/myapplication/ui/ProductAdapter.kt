package architecture.basic.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import architecture.basic.myapplication.R
import architecture.basic.myapplication.databinding.CommentItemBinding
import architecture.basic.myapplication.databinding.ProductItemBinding
import architecture.basic.myapplication.db.entities.ProductEntity

class ProductAdapter(private val mProductClickCallback: ProductClickCallback?) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    internal var mProductList: List<ProductEntity>? = null
    fun setProductList(productList: List<ProductEntity>) {
        if (mProductList == null) {
            mProductList = productList
            notifyItemRangeInserted(0, productList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mProductList!!.size
                }

                override fun getNewListSize(): Int {
                    return productList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mProductList!![oldItemPosition].id == productList[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newProduct = productList[newItemPosition]
                    val oldProduct = mProductList!![oldItemPosition]
                    return newProduct.id == oldProduct.id && newProduct.productName == oldProduct.productName
                }
            })
            mProductList = productList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var  binding : ProductItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.product_item,
                        parent, false)
        binding.callback = mProductClickCallback
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.product = mProductList!![position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (mProductList == null) 0 else mProductList!!.size
    }

      class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)
}
