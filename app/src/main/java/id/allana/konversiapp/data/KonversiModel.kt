package id.allana.konversiapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "konversi")
@Parcelize
data class KonversiModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "nis")
    val nis: Int,

    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "kelas")
    val kelas: String,

    @ColumnInfo(name = "nama_dudi")
    val namaDudi: String,

    @ColumnInfo(name = "alamat_dudi")
    val alamatDudi: String,

    @ColumnInfo(name = "durasi_pkl")
    val durasiPkl: String,

    @ColumnInfo(name = "manajerial")
    val manajerial: String,

    @ColumnInfo(name = "kepemimpinan")
    val kepemimpinan: String,

    @ColumnInfo(name = "kejuruan")
    val kejuruan: String,

    @ColumnInfo(name = "kerja_tim")
    val kerjaTim: String,

    @ColumnInfo(name = "kedisiplinan")
    val kedisiplinan: String,

    @ColumnInfo(name = "rerata")
    val rerata: String,

    @ColumnInfo(name = "predikat")
    val predikat: String,

    @ColumnInfo(name = "keterangan")
    val keterangan: String
): Parcelable