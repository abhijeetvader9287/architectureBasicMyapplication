package architecture.basic.myapplication.ui

import architecture.basic.myapplication.db.entities.CommentEntity

interface CommentClickCallback {
    fun onClick(comment: CommentEntity)
}
