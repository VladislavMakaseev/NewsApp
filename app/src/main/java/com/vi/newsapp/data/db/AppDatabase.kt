package com.vi.newsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vi.newsapp.data.articles.ArticleEntity
import com.vi.newsapp.data.articles.ArticlesDao

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val ROOM_DB_NAME = "room_db"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                ROOM_DB_NAME
            ).build()
        }
    }

    abstract fun articlesDao(): ArticlesDao

}