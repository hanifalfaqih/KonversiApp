package id.allana.konversiapp.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import id.allana.konversiapp.R
import id.allana.konversiapp.data.KonversiModel
import id.allana.konversiapp.databinding.ActivityAddKonversiBinding
import id.allana.konversiapp.databinding.ActivityMainBinding
import id.allana.konversiapp.ui.add.AddKonversiActivity
import id.allana.konversiapp.ui.add.AddKonversiViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter: KonversiAdapter by lazy {
        KonversiAdapter { konversi ->
            showAlertDialog(konversi)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fabTambahKonversi.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddKonversiActivity::class.java))
            }
        }

        initRecyclerView()
        observeData()
    }

    private fun initRecyclerView() {
        binding.rvHasilKonversi.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun observeData() {
        viewModel.getAllCategory().observe(this) {
            adapter.submitList(it)
        }
    }

    private fun showAlertDialog(data: KonversiModel) {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Hapus Data")
            setMessage("Anda yakin ingin menghapus data dengan NIS ${data.nis} dan NAMA ${data.nama}?")
            setPositiveButton("Hapus") { _, _ ->
                viewModel.deleteKonversi(data)
                Snackbar.make(this@MainActivity.binding.root, "Berhasil hapus data", Snackbar.LENGTH_SHORT).show()
            }
            setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }
}