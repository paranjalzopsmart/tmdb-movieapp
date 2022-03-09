package com.example.tmdb.models.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.models.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    object DatabaseBuilder {

        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "Room-DB-coroutines"
            ).build()
    }
}
