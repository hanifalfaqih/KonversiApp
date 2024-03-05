package id.allana.konversiapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KonversiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKonversi(konversi: KonversiModel)
    @Delete
    suspend fun deleteKonversi(konversi: KonversiModel)
    @Query("SELECT * FROM konversi ORDER BY id DESC")
    fun getAllKonversi(): LiveData<List<KonversiModel>>
}