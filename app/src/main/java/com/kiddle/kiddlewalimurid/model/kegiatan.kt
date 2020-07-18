package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class kegiatan(
    val judul : String,
    val img : Int
): Parcelable