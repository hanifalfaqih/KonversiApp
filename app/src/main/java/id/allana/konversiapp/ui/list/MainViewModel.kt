package id.allana.konversiapp.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.allana.konversiapp.data.KonversiDatabase
import id.allana.konversiapp.data.KonversiModel
import id.allana.konversiapp.data.KonversiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val konversiDao = KonversiDatabase.getDatabase(application).konversiDao()
    private val repository = KonversiRepository(konversiDao)

    fun getAllCategory(): LiveData<List<KonversiModel>> = repository.getAllKonversi()

    fun deleteKonversi(konversi: KonversiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteKonversi(konversi)
        }
    }

}