package architecture.basic.myapplication.viewmodels

import android.app.Application
import androidx.annotation.NonNull
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import architecture.basic.myapplication.BasicApp
import architecture.basic.myapplication.DataRepository
import architecture.basic.myapplication.db.entities.CommentEntity
import architecture.basic.myapplication.db.entities.ProductEntity

class ProductViewModel(@NonNull application: Application, repository: DataRepository?,
                       private val mProductId: Int) : AndroidViewModel(application) {
    val observableProduct: LiveData<ProductEntity>?
    var product: ObservableField<ProductEntity> = ObservableField()
    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    val comments: LiveData<List<CommentEntity>>?

    init {
        comments = repository?.loadComments(mProductId)
        observableProduct = repository?.loadProduct(mProductId)
    }

    fun setProduct(product: ProductEntity) {
        this.product.set(product)
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     *
     *
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    class Factory(@param:NonNull @field:NonNull
                  private val mApplication: Application, private val mProductId: Int) : ViewModelProvider.NewInstanceFactory() {
        private val mRepository: DataRepository?

        init {
            mRepository = (mApplication as BasicApp).repository
        }
override
        fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductViewModel(mApplication, mRepository, mProductId) as T
        }
    }
}
