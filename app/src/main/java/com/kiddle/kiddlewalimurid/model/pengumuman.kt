package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class pengumuman(
    val judul : String,
    val tanggal : String,
    val img : Int

): Parcelable