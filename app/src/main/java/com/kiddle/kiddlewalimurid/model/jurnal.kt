package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class jurnal(
    val judul : String,
    val tanggal : String,
    val bidang : String
): Parcelable

