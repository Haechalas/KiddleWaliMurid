package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemBayarSpp(
  var batas:String?,
  var bulan:String?,
  var harga:String?,
  var semester:String?
): Parcelable