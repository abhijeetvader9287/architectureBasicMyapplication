package architecture.basic.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import architecture.basic.myapplication.R
import architecture.basic.myapplication.databinding.ListFragmentBinding
import architecture.basic.myapplication.db.entities.ProductEntity
import architecture.basic.myapplication.viewmodels.ProductListViewModel

class ProductListFragment : Fragment() {
    private var mProductAdapter: ProductAdapter? = null
    private var mBinding: ListFragmentBinding? = null
    private val mProductClickCallback = object : ProductClickCallback {
        override fun onClick(product: ProductEntity) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(product)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mBinding = DataBindingUtil .inflate(inflater, R.layout.list_fragment, container, false)

        mProductAdapter = ProductAdapter(mProductClickCallback)
        mBinding!!.productsList.adapter = mProductAdapter

        return mBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)

        subscribeUi(viewModel)
    }

    private fun subscribeUi(viewModel: ProductListViewModel) {
        // Update the list when the data changes
        viewModel.products.observe(this, Observer { myProducts ->
            if (myProducts != null) {
                mBinding!!.isLoading = false
                mProductAdapter!!.setProductList(myProducts)
            } else {
                mBinding!!.isLoading = true
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mBinding!!.executePendingBindings()
        })
    }

    companion object {
        val TAG = "ProductListViewModel"
    }
}
