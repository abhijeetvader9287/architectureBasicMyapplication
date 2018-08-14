package architecture.basic.myapplication

import android.app.Application
import architecture.basic.myapplication.db.AppDatabase

class BasicApp : Application() {
    private var mAppExecutors: AppExecutors? = null
    lateinit var database: AppDatabase

    lateinit var repository: DataRepository


    override fun onCreate() {
        super.onCreate()

        mAppExecutors = AppExecutors()
        database = AppDatabase.getInstance(this, this!!.mAppExecutors)!!
        repository= DataRepository.getInstance(database)!!
    }
}




