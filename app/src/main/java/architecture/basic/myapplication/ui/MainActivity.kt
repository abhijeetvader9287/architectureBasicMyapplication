package architecture.basic.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import architecture.basic.myapplication.R
import architecture.basic.myapplication.db.entities.ProductEntity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ProductListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ProductListFragment.TAG).commit()
        }
    }

    /** Shows the product detail fragment  */
    fun show(product: ProductEntity) {
        val productFragment = ProductFragment.forProduct(product.id)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container,
                        productFragment, null).commit()
    }
}
