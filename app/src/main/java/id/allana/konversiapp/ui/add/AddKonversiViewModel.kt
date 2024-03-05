package id.allana.konversiapp.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import id.allana.konversiapp.data.KonversiDatabase
import id.allana.konversiapp.data.KonversiModel
import id.allana.konversiapp.data.KonversiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddKonversiViewModel(application: Application): AndroidViewModel(application) {

    private val konversiDao = KonversiDatabase.getDatabase(application).konversiDao()
    private val repository = KonversiRepository(konversiDao)

    fun insertKonversi(konversi: KonversiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertKonversi(konversi)
        }
    }
}