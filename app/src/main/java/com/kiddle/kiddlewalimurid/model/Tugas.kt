package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//menggunakan parcelable agar bisa mengambil data sekaligus dari firebase
@Parcelize
data class Tugas (
    val id:String?,
    val kelas :String?,
    val judul:String?,
    val isi : String?,
    val tanggal:String?,
    val jam: String?,
    val jumlah:String?,
    val gambar:String?,
    val video:String?,
    val link:String?
): Parcelable