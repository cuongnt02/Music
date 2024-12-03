package com.ntc.music

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class Song(val title: String, var duration: Int, val compose: String, val present: String, @RawRes val track: Int, @StringRes val lyrics: Int, @DrawableRes val image: Int)