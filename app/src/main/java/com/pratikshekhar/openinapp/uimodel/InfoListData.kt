package com.pratikshekhar.openinapp.uimodel

import androidx.annotation.DrawableRes

data class InfoListData(
    @DrawableRes val drawableRes: Int,
    val label:String,
    val description:String
)
