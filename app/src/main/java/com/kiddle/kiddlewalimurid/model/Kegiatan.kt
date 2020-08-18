package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//menggunakan parcelable agar bisa mengambil data sekaligus dari firebase
@Parcelize
data class Kegiatan(
    val judul: String?,
    val isi: String?,
    val tanggal: String?,
    val gambar: String?,
    val video: String?,
    val link: String?
) : Parcelable