package id.allana.konversiapp.data

import androidx.lifecycle.LiveData

class KonversiRepository(private val konversiDao: KonversiDao) {
    suspend fun insertKonversi(konversi: KonversiModel) {
        konversiDao.insertKonversi(konversi)
    }

    suspend fun deleteKonversi(konversi: KonversiModel) {
        konversiDao.deleteKonversi(konversi)
    }

    fun getAllKonversi(): LiveData<List<KonversiModel>> = konversiDao.getAllKonversi()
}