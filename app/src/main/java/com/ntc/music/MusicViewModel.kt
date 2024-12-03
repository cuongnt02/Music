package com.ntc.music

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
class MusicViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val musicList = listOf (
        Song(title="Rocking around a christmas tree", duration=0, compose="Johnny Marks", present="Brenda Lee", track=R.raw.rocking_around_the_christmas_tree, lyrics=R.string.christmas_song1_lyrics, image = R.drawable.rocking_around_the_christmas_tree),
        Song(title="All I want for christmas is you", duration=0, compose="Mariah Carey, Walter Afanasieff", present="Mariah Carey", track=R.raw.all_i_want_for_christmas_is_you, lyrics=R.string.christmas_song2_lyrics, image=R.drawable.all_i_want_for_christmas_is_you)
    )

    private var currentIndex
        get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    private val currentSong: Song
        get() = musicList[currentIndex]

    val currentTrack: Int
        get() = currentSong.track

    var currentDuration: Int
        get() = currentSong.duration
        set(value) {
            currentSong.duration = value
        }

    val currentImage: Int
        get() = currentSong.image

    val currentTitle: String
        get() = currentSong.title

    val currentLyrics: Int
        get() = currentSong.lyrics

    fun nextSong() {
        val currentIncrement = (if (currentIndex + 1 < musicList.size) 1 else 0)
        currentIndex += currentIncrement
    }

    fun previousSong(){
        val currentDecrement = (if (currentIndex - 1 >= 0) 1 else 0)
        currentIndex -= currentDecrement
    }
}