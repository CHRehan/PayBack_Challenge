package com.payback.challenge.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.payback.challenge.features.pixabay.data.datasource.local.ImageDetailsDao
import com.payback.challenge.features.pixabay.data.datasource.local.entities.ImageDetailsEntity

@Database(
    entities = [ImageDetailsEntity::class],
    version = PixabayDb.VERSION,
    exportSchema = false
)
abstract class PixabayDb : RoomDatabase() {

    abstract fun PixabayDao(): ImageDetailsDao

    companion object {
        internal const val VERSION = 1
        private const val NAME = "pixabay_db"

        fun create(applicationContext: Context): PixabayDb {
            return Room.databaseBuilder(
                applicationContext,
                PixabayDb::class.java,
                NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}
