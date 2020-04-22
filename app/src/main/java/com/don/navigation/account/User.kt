package com.don.navigation.account

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by don on 22,April,2020
 */
@Parcelize
data class User(val name: String, val address: String) : Parcelable