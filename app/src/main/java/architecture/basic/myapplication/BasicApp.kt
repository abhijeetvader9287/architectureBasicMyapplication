package architecture.basic.myapplication

import android.app.Application
import architecture.basic.myapplication.db.MyDatabase

class BasicApp : Application() {

    lateinit var database: MyDatabase

    lateinit var repository: DataRepository


    override fun onCreate() {
        super.onCreate()


        database = MyDatabase.getAppDatabase(this)
        repository = DataRepository.getInstance(database)!!
    }


}
