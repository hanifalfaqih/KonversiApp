package id.allana.konversiapp.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import id.allana.konversiapp.R
import id.allana.konversiapp.data.KonversiModel
import id.allana.konversiapp.databinding.ActivityAddKonversiBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddKonversiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddKonversiBinding
    private val viewModel: AddKonversiViewModel by lazy {
        ViewModelProvider(this)[AddKonversiViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddKonversiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnKonversi.setOnClickListener {
                if (validateForm()) {
                    setPredikatAndDeskripsi(calculateKonversi())
                    btnKonversi.isEnabled = false
                    btnIsiLagi.isVisible = true
                    btnSimpan.isVisible = true
                } else {
                    Snackbar.make(this.root.rootView, "Data masih ada yang kosong", Snackbar.LENGTH_SHORT).show()
                }
            }

            btnIsiLagi.setOnClickListener {
                binding.apply {
                    edtNis.text?.clear()
                    edtNama.text?.clear()
                    edtKelas.text?.clear()
                    edtNamaDudi.text?.clear()
                    edtAlamatDudi.text?.clear()
                    edtDurasiPkl.text?.clear()
                    edtManajerial.text?.clear()
                    edtKedisiplinan.text?.clear()
                    edtKerjaTim.text?.clear()
                    edtKepemimpinan.text?.clear()
                    edtKejuruan.text?.clear()
                    tvRerata.text = "-"
                    tvPredikat.text = "-"
                    tvKeterangan.text = "-"
                }
            }

            btnSimpan.setOnClickListener {
                viewModel.insertKonversi(KonversiModel(
                    nis = edtNis.text.toString().toInt(),
                    nama = edtNama.text.toString(),
                    kelas = edtKelas.text.toString(),
                    namaDudi = edtNamaDudi.text.toString(),
                    alamatDudi = edtAlamatDudi.text.toString(),
                    durasiPkl = edtDurasiPkl.text.toString(),
                    manajerial = edtManajerial.text.toString(),
                    kedisiplinan = edtKedisiplinan.text.toString(),
                    kejuruan = edtKejuruan.text.toString(),
                    kerjaTim = edtKerjaTim.text.toString(),
                    kepemimpinan = edtKepemimpinan.text.toString(),
                    rerata = tvRerata.text.toString(),
                    predikat = tvPredikat.text.toString(),
                    keterangan = tvKeterangan.text.toString()
                ))
                lifecycleScope.launch {
                    Snackbar.make(
                        this@AddKonversiActivity.binding.root.rootView,
                        "Data berhasil disimpan",
                        Snackbar.LENGTH_SHORT
                    ).setDuration(2000).show()
                    delay(1000)
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }

    }

    private fun calculateKonversi(): Int {
        binding.apply {
            val average = (
                    edtManajerial.text.toString().toInt() +
                    edtKepemimpinan.text.toString().toInt() +
                    edtKejuruan.text.toString().toInt() +
                    edtKerjaTim.text.toString().toInt() +
                    edtKedisiplinan.text.toString().toInt()
            ) / 5

            return average
        }
    }

    private fun setPredikatAndDeskripsi(rerata: Int) {
        binding.apply {
            when (rerata) {
                in 91..100 -> {
                    tvRerata.text = rerata.toString()
                    tvPredikat.text = "Amat Baik"
                    tvKeterangan.text = "Kompeten"
                }
                in 86..90 -> {
                    tvRerata.text = rerata.toString()
                    tvPredikat.text = "Baik"
                    tvKeterangan.text = "Kompeten"
                }
                in 80..85 -> {
                    tvRerata.text = rerata.toString()
                    tvPredikat.text = "Cukup"
                    tvKeterangan.text = "Kompeten"
                }
                in 70..79 -> {
                    tvRerata.text = rerata.toString()
                    tvPredikat.text = "Kurang"
                    tvKeterangan.text = "Belum Kompeten"
                }
                else -> {
                    if (rerata > 100) {
                        tvRerata.text = rerata.toString()
                        tvPredikat.text = "Lebih dari 100"
                        tvKeterangan.text = "Hasil tidak valid"
                    } else {
                        tvRerata.text = rerata.toString()
                        tvPredikat.text = "Sangat Kurang"
                        tvKeterangan.text = "Belum Kompeten"
                    }
                }
            }
        }
    }

    private fun validateForm(): Boolean{
        binding.apply {
            return edtNis.text.toString().isNotEmpty() &&
                    edtNama.text.toString().isNotEmpty() &&
                    edtKelas.text.toString().isNotEmpty() &&
                    edtNamaDudi.text.toString().isNotEmpty() &&
                    edtAlamatDudi.text.toString().isNotEmpty() &&
                    edtDurasiPkl.text.toString().isNotEmpty() &&
                    edtManajerial.text.toString().isNotEmpty() &&
                    edtKedisiplinan.text.toString().isNotEmpty() &&
                    edtKerjaTim.text.toString().isNotEmpty() &&
                    edtKepemimpinan.text.toString().isNotEmpty() &&
                    edtKejuruan.text.toString().isNotEmpty()
        }
    }
}