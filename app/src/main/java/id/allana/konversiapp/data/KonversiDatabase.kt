package id.allana.konversiapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [KonversiModel::class], version = 1, exportSchema = false)
abstract class KonversiDatabase: RoomDatabase() {

    abstract fun konversiDao(): KonversiDao

    companion object {
        @Volatile
        private var INSTANCE: KonversiDatabase? = null

        fun getDatabase(context: Context): KonversiDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KonversiDatabase::class.java,
                    "konversi_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}