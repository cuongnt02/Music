package com.ntc.music

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ntc.music.databinding.ActivityLyricsBinding

const val EXTRA_LYRICS_CONTENT = "com.ntc.music.lyrics_content"
const val EXTRA_TITLE_CONTENT = "com.ntc.music.title_content"
class LyricsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLyricsBinding

    private val lyricsViewModel : LyricsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLyricsBinding.inflate(layoutInflater)

        val titleText = intent.getStringExtra(EXTRA_TITLE_CONTENT) ?: ""
        val contentText = intent.getStringExtra(EXTRA_LYRICS_CONTENT) ?: ""

        lyricsViewModel.currentLyrics = contentText
        lyricsViewModel.currentSongTitle = titleText

        binding.lyricsTitleText.text = lyricsViewModel.currentLyrics
        binding.lyricsContentText.text = lyricsViewModel.currentSongTitle


        setContentView(binding.root)
    }

    companion object {
        fun newIntent(packageContext: Context, songTitle: String, lyricsContent: String): Intent {
            val intent = Intent(packageContext, LyricsActivity::class.java)
            intent.putExtra(EXTRA_LYRICS_CONTENT, lyricsContent)
            intent.putExtra(EXTRA_TITLE_CONTENT, songTitle)
            return intent
        }
    }
}