package com.example.dukaan.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class,StoreEntity::class,ProductEntity::class],version = 2)
abstract class DukaanRoomDatabase : RoomDatabase() {
    abstract fun getDukaan(): DukaanRoomDAO

    companion object {
        private var INSTANCE: DukaanRoomDatabase? = null
        fun getDatabaseContext(context: Context): DukaanRoomDatabase {
            if (INSTANCE == null) {

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    DukaanRoomDatabase::class.java,
                    "dukaan")
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()

                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }
    }
}