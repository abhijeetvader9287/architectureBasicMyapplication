package architecture.basic.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import architecture.basic.myapplication.db.AppDatabase
import architecture.basic.myapplication.db.entities.CommentEntity
import architecture.basic.myapplication.db.entities.ProductEntity

class DataRepository private constructor(private val mDatabase: AppDatabase?) {
    private val mObservableProducts: LiveData<List<ProductEntity>>
    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    val products: LiveData<List<ProductEntity>>
        get() = mObservableProducts

    init {
        mObservableProducts = MediatorLiveData()

        mObservableProducts.addSource(mDatabase?.productDao()?.loadAllProducts() as LiveData<List<ProductEntity>>,
                { productEntities ->

                        mObservableProducts.postValue(productEntities)

                })
    }

    fun loadProduct(productId: Int): LiveData<ProductEntity>? {
        return mDatabase?.productDao()?.loadProduct(productId)
    }

    fun loadComments(productId: Int): LiveData<List<CommentEntity>>? {
        return mDatabase?.commentDao()?.loadComments(productId)
    }

    companion object {
        private var sInstance: DataRepository? = null
        fun getInstance(database: AppDatabase?): DataRepository? {
            if (sInstance == null) {
                synchronized(DataRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = DataRepository(database)
                    }
                }
            }
            return sInstance
        }
    }
}
