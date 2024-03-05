package id.allana.konversiapp.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import id.allana.konversiapp.R
import id.allana.konversiapp.data.KonversiModel
import id.allana.konversiapp.databinding.ActivityDetailKonversiBinding

class DetailKonversiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailKonversiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKonversiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val data = intent.getParcelableExtra<KonversiModel>(DETAIL_KONVERSI)
        data?.let {
            setData(it)
        }
    }

    private fun setData(data: KonversiModel) {
        binding.apply {
            tvNis.text = data.nis.toString()
            tvNamaSiswa.text = data.nama
            tvKelas.text = data.kelas
            tvNamaDudi.text = data.namaDudi
            tvAlamatDudi.text = data.alamatDudi
            tvDurasiPkl.text = data.durasiPkl
            tvManajerial.text = data.manajerial
            tvKedisiplinan.text = data.kedisiplinan
            tvKepemimpinan.text = data.kepemimpinan
            tvKerjaTim.text = data.kerjaTim
            tvKejuruan.text = data.kejuruan
            tvRerata.text = data.rerata
            tvPredikat.text = data.predikat
            tvKeterangan.text = data.keterangan
        }
    }

    companion object {
        const val DETAIL_KONVERSI = "detail_konversi"
    }
}