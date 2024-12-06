package com.ntc.music

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


const val CURRENT_LYRICS_KEY = "CURRENT_LYRICS_KEY"
const val CURRENT_SONG_TITLE_KEY = "CURRENT_SONG_TITLE_KEY"
class LyricsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentLyrics: Int
        get() = savedStateHandle[CURRENT_LYRICS_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_LYRICS_KEY, value)

    var currentSongTitle: String
        get() = savedStateHandle[CURRENT_SONG_TITLE_KEY] ?: ""
        set(value) = savedStateHandle.set(CURRENT_SONG_TITLE_KEY, value)


    fun getLyricsContent(ctx: Context): String = ctx.resources.openRawResource(currentLyrics).readBytes().toString(charset = Charsets.UTF_8)

}