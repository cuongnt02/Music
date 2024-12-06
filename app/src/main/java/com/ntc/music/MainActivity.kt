package com.ntc.music

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.ntc.music.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val musicViewModel: MusicViewModel by viewModels()
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        
        binding.playButton.setOnClickListener {
            animatePlayButton()
            binding.songImage.setImageResource(musicViewModel.currentImage)
        }

        binding.playPauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                pauseCurrent()
                binding.playPauseButton.setImageResource(R.drawable.play_24)
            } else {
                resumeCurrent()
                binding.playPauseButton.setImageResource(R.drawable.pause_24)
            }
        }

        binding.nextButton.setOnClickListener {
            nextTrack()
        }

        binding.prevButton.setOnClickListener {
            previousTrack()
        }

        binding.lyricsButton.setOnClickListener {
            openLyrics()
        }

        binding.settingsButton.setOnClickListener {
            openSetting()
        }

        binding.songListButton.setOnClickListener {
            openSongList()
        }


    }

    private fun removeButton() {
        binding.playButton.visibility = View.GONE
        binding.playButton.scaleX = 1.0f
        binding.playButton.scaleY = 1.0f
        binding.playButton.translationX = 0.0f
        binding.playButton.translationY = 0.0f
    }


    private fun showSongImage() {
        binding.songImage.visibility = View.VISIBLE
    }

    private fun showControls() {
        binding.controlButtonsLayout.visibility = View.VISIBLE
    }

    private fun animatePlayButton() {

        val moveLeftAnim = ObjectAnimator.ofFloat(binding.playButton, "translationX", -350f)
        val zoomOutXAnim = ObjectAnimator.ofFloat(binding.playButton, "scaleX", 0.2f)
        val zoomOutYAnim = ObjectAnimator.ofFloat(binding.playButton, "scaleY", 0.2f)
        val moveUpAnim = ObjectAnimator.ofFloat(binding.playButton, "translationY", -100f)
        val dropDownAnim = ObjectAnimator.ofFloat(binding.playButton, "translationY", 600f)
        val boxZoomInX = ObjectAnimator.ofFloat(binding.musicBoxImage, "scaleX", 1.2f)
        val boxZoomInY = ObjectAnimator.ofFloat(binding.musicBoxImage, "scaleY", 1.2f)
        val boxZoomOutX = ObjectAnimator.ofFloat(binding.musicBoxImage, "scaleX", 1f)
        val boxZoomOutY = ObjectAnimator.ofFloat(binding.musicBoxImage, "scaleY", 1f)
        val fadeOutAnim = ObjectAnimator.ofFloat(binding.playButton, "alpha", 0.0f)
        val boxFadeInAnim = ObjectAnimator.ofFloat(binding.musicBoxImage, "alpha", 1.0f)
        val boxFadeOutAnim = ObjectAnimator.ofFloat(binding.musicBoxImage, "alpha", -1.0f)

        val zoomMoveSet = AnimatorSet().apply {
            play(moveLeftAnim)
                .with(zoomOutXAnim)
                .with(zoomOutYAnim)
                .with(boxFadeInAnim)
            duration = 1000
        }
        val boxZoomInOutSet = AnimatorSet().apply {
            play(boxZoomInX).with(boxZoomInY).before(boxZoomOutX).before(boxZoomOutY).before(boxFadeOutAnim)
        }
        val dropDownSet = AnimatorSet().apply {
            play(moveUpAnim)
                .before(boxZoomInOutSet)
                .before(dropDownAnim)
                .before(fadeOutAnim)
            duration = 1000
        }
        AnimatorSet().apply {
            play(zoomMoveSet).before(dropDownSet)
            start()
            doOnEnd {
                removeButton()
                showSongImage()
                playCurrent()
                showControls()
            }
        }
    }

    private fun playCurrent() {
        mediaPlayer = MediaPlayer.create(this@MainActivity, musicViewModel.currentTrack)
        musicViewModel.currentDuration = mediaPlayer.duration
        binding.songTitleText.text = getString(R.string.now_playing, musicViewModel.currentTitle)
        binding.songImage.setImageResource(musicViewModel.currentImage)
        mediaPlayer.start()
    }

    private fun pauseCurrent() {
        mediaPlayer.pause()
    }

    private fun resumeCurrent() {
        mediaPlayer.start()
    }

    private fun stopCurrent() {
        mediaPlayer.stop()
    }

    private fun nextTrack() {
        stopCurrent()
        musicViewModel.nextSong()
        playCurrent()
    }

    private fun previousTrack() {
        stopCurrent()
        musicViewModel.previousSong()
        playCurrent()
    }

    private fun openLyrics() {
        val lyricsId = musicViewModel.currentLyrics
        val songTitle = musicViewModel.currentTitle
        val intentToLyrics = LyricsActivity.newIntent(this@MainActivity, songTitle, lyricsId)
        startActivity(intentToLyrics)
    }

    private fun openSongList() {

    }

    private fun openSetting() {

    }
}