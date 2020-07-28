package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//menggunakan parcelable agar bisa mengambil data sekaligus dari firebase. ganti semua ke String!
@Parcelize
data class Guru(
    val avatar: Int,
    val nama: String,
    val nomor: String,
    val kontak: String,
    val jabatan: String,
    val password: String
) : Parcelable