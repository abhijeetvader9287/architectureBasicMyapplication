package architecture.basic.myapplication.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import architecture.basic.myapplication.db.entities.CommentEntity

@Dao
interface CommentDao {
    @Query("Select * from comments where productId=:productId")
    fun loadComments(productId: Int): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments where productId = :productId")
    fun loadCommentsSync(productId: Int): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comments: List<CommentEntity>)
}
