package architecture.basic.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import architecture.basic.myapplication.BasicApp
import architecture.basic.myapplication.db.entities.ProductEntity

class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private val mObservableProducts: MediatorLiveData<List<ProductEntity>>
    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    val products: LiveData<List<ProductEntity>>
        get() = mObservableProducts

    init {
        mObservableProducts = MediatorLiveData()
        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null)
        val products = (application as BasicApp).repository?.products
        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource(products!!,({ mObservableProducts.value }))
    }
}
