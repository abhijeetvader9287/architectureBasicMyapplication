package architecture.basic.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import architecture.basic.myapplication.R
import architecture.basic.myapplication.databinding.CommentItemBinding
import architecture.basic.myapplication.db.entities.CommentEntity

class CommentAdapter(private val mCommentClickCallback: CommentClickCallback?) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    override fun getItemCount(): Int {
       return if (mCommentList == null) 0 else mCommentList!!.size
    }

    private var mCommentList: List<CommentEntity>? = null

    fun setCommentList(comments: List<CommentEntity>) {
        if (mCommentList == null) {
            mCommentList = comments
            notifyItemRangeInserted(0, comments.size)
        } else {
            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mCommentList!!.size                }

                override fun getNewListSize(): Int {
                    return comments.size   }

                override   fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val old = mCommentList!![oldItemPosition]
                    val comment = comments[newItemPosition]
                    return old.id === comment.id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val old = mCommentList!![oldItemPosition]
                    val comment = comments[newItemPosition]
                    return (old.id === comment.id && old.productId === comment.productId )
                }
            })
            mCommentList = comments
            diffResult.dispatchUpdatesTo(this)
        }
    }

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
          var  binding :CommentItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.comment_item,
                        parent, false)
        binding.setCallback(mCommentClickCallback)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.comment= mCommentList!![position]
        holder.binding.executePendingBindings()
    }

      class CommentViewHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.getRoot())
}
