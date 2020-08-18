package com.kiddle.kiddlewalimurid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemBayarSpp(
   var avatar:String?,
   var bukti:String?,
   var bulan:String?,
   var harga:String?,
   var nama:String?,
   var status:String?,
   var tanggal:String?
): Parcelable