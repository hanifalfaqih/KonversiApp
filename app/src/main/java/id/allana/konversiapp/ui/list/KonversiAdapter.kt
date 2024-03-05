package id.allana.konversiapp.ui.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.allana.konversiapp.data.KonversiModel
import id.allana.konversiapp.databinding.ItemHasilKonversiBinding
import id.allana.konversiapp.ui.detail.DetailKonversiActivity

class KonversiAdapter(private val itemKonversi: (KonversiModel) -> Unit): ListAdapter<KonversiModel, KonversiAdapter.KonversiViewHolder>(KonversiComparator()) {

    inner class KonversiViewHolder(private val binding: ItemHasilKonversiBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: KonversiModel) {
            binding.apply {
                tvNis.text = data.nis.toString()
                tvNamaSiswa.text = data.nama

                btnHapus.setOnClickListener {
                    itemKonversi(data)
                }

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailKonversiActivity::class.java)
                    intent.putExtra(DetailKonversiActivity.DETAIL_KONVERSI, data)
                    it.context.startActivity(intent)
                }
            }
        }
    }
    class KonversiComparator: DiffUtil.ItemCallback<KonversiModel>() {
        override fun areItemsTheSame(oldItem: KonversiModel, newItem: KonversiModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: KonversiModel, newItem: KonversiModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonversiViewHolder {
        val binding = ItemHasilKonversiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KonversiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KonversiViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}