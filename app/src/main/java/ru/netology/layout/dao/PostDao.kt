package ru.netology.layout.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao

import androidx.room.Insert
import androidx.room.Query
import ru.netology.layout.dto.Post

import ru.netology.layout.entity.PostEntity


@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<PostEntity>>
    @Insert
    fun insert(post: Post)

    @Query("UPDATE PostEntity SET content = :content WHERE id = :id")
    fun updateContentById(id: Long, content: String)

    @Query(
        """
                UPDATE PostEntity SET
                likes = likes + CASE WHEN liked THEN -1 ELSE 1 END,
                liked = CASE WHEN liked THEN 0 ELSE 1 END
                WHERE id = :id;
                """
    )
    fun likeById(id: Long)
    @Query(
        """
                UPDATE PostEntity SET
                shares = shares + 1
                WHERE id = :id;
                """
    )
    fun shareById(id: Long)
    @Query(
        """
                UPDATE PostEntity SET
                views = views + 1
                WHERE id = :id;
                """
    )
    fun viewById(id: Long)
    @Query("DELETE FROM PostEntity WHERE id = :id")
    fun removeById(id: Long)
    fun save(post: Post) =
        if (post.id == 0L) insert(post) else updateContentById(post.id, post.content)

}