package com.example.scrollablecompose.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Fallout(
    val id: Int,
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val wikiResId: Int,
    @StringRes val yearId: Int,
    @StringRes val detailResId: Int
)