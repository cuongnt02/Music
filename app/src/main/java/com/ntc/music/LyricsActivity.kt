package com.ntc.music

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val EXTRA_LYRICS_CONTENT = "com.ntc.music.lyrics_content"
class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)
    }

    companion object {
        fun newIntent(packageContext: Context, lyricsContent: String): Intent {
            val intent = Intent(packageContext, LyricsActivity::class.java)
            intent.putExtra(EXTRA_LYRICS_CONTENT, lyricsContent)
            return intent
        }
    }
}