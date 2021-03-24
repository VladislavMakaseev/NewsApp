package com.vi.newsapp.data.articles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    fun getAll(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE id = :id")
    fun getArticleById(id: Long): ArticleEntity

    @Insert
    fun insert(entities: List<ArticleEntity>)

    @Query("SELECT COUNT(id) FROM articles")
    fun count(): Int

    @Update
    fun update(entities: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    fun clear()

}