package architecture.basic.myapplication

import android.app.Application
import architecture.basic.myapplication.db.AppDatabase

class BasicApp : Application() {
    private var mAppExecutors: AppExecutors? = null
    val database: AppDatabase?
        get() = AppDatabase.getInstance(this, mAppExecutors)
    val repository: DataRepository?
        get() = DataRepository.getInstance(database)

    override fun onCreate() {
        super.onCreate()

        mAppExecutors = AppExecutors()
    }
}
