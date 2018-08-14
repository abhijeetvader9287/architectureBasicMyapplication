package architecture.basic.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import architecture.basic.myapplication.R
import architecture.basic.myapplication.databinding.ProductFragmentBinding
import architecture.basic.myapplication.db.entities.CommentEntity
import architecture.basic.myapplication.db.entities.ProductEntity
import architecture.basic.myapplication.viewmodels.ProductViewModel

class ProductFragment : Fragment() {
    private var mBinding: ProductFragmentBinding? = null
    private var mCommentAdapter: CommentAdapter? = null
    private val mCommentClickCallback = object : CommentClickCallback {
        override fun onClick(comment: CommentEntity) {
            // no-op
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false)
        // Create and set the adapter for the RecyclerView.
        mCommentAdapter = CommentAdapter(mCommentClickCallback)
        mBinding!!.commentList.adapter = mCommentAdapter
        return mBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ProductViewModel.Factory(
                activity!!.application, arguments!!.getInt(KEY_PRODUCT_ID))
        val model = ViewModelProviders.of(this, factory)
                .get(ProductViewModel::class.java)

        mBinding!!.productViewModel = model

        subscribeToModel(model)
    }

    private fun subscribeToModel(model: ProductViewModel) {
        // Observe product data
        model.observableProduct!!.observe(this, Observer { productEntity -> model.setProduct(productEntity!!) })
        // Observe comments
        model.comments!!.observe(this, Observer { commentEntities ->
            if (commentEntities != null) {
                mBinding!!.isLoading = false
                mCommentAdapter!!.setCommentList(commentEntities)
            } else {
                mBinding!!.isLoading = true
            }
        })
    }

    companion object {
        private val KEY_PRODUCT_ID = "product_id"
        /** Creates product fragment for specific product ID  */
        fun forProduct(productId: Int): ProductFragment {
            val fragment = ProductFragment()
            val args = Bundle()
            args.putInt(KEY_PRODUCT_ID, productId)
            fragment.arguments = args
            return fragment
        }
    }
}
