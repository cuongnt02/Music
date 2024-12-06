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
        val lyricsId = intent.getIntExtra(EXTRA_LYRICS_CONTENT, 0)


        lyricsViewModel.currentSongTitle = titleText
        lyricsViewModel.currentLyrics = lyricsId

        val lyricsContent = lyricsViewModel.getLyricsContent(this@LyricsActivity)

        binding.lyricsTitleText.text = lyricsViewModel.currentSongTitle
        binding.lyricsContentText.text = lyricsContent


        setContentView(binding.root)
    }


    companion object {
        fun newIntent(packageContext: Context, songTitle: String, lyricsContent: Int): Intent{
            val intent = Intent(packageContext, LyricsActivity::class.java)
            intent.putExtra(EXTRA_LYRICS_CONTENT, lyricsContent)
            intent.putExtra(EXTRA_TITLE_CONTENT, songTitle)
            return intent
        }
    }
}