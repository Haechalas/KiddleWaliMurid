package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemBayarSpp(
    var idMurid: String? = null,
    var bulan: String? = null,
    var status: String? = null,
    var bukti: Int? = null
): Parcelable