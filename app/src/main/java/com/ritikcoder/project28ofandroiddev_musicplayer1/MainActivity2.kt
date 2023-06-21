package com.ritikcoder.project28ofandroiddev_musicplayer1

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.os.trace
import com.ritikcoder.project28ofandroiddev_musicplayer1.databinding.ActivityMain2Binding
import java.lang.Exception

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    lateinit var mediaPlayer: MediaPlayer
    var traversing = 0
    var totalTime: Int = 0
    var stopValidation: Boolean = true
    var playValidation: Boolean = true
    var pauseValidation: Boolean = true
    private var currentImage = 1
    private lateinit var image: ImageView
    var nextButtonValidation: Boolean = true
    var previousButtonValidation: Boolean = true

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hiding of pause button
        binding.imageViewForPause.alpha = 0f
        binding.imageViewForStop.alpha = 0f

        val musicLocationArray = intArrayOf(R.raw.music1, R.raw.music2, R.raw.music3, R.raw.music4, R.raw.music5)
        val musicTittleArray= arrayOf("Hue Bechain - Slowed Reverb Lofi _ Song",
            "Shaam Bhi Khoob Hai Paas Mehboob Hai", "DJ Barıs Demir _ (ClubRemix)",
            "Bom diggy diggy bom bom (slow reverb)", "Rim Jhim - Khan Saab ft. Pav Dharia ")

        //Calling create music Player method.
        createMusicPlayer(musicLocationArray[0])

        binding.imageViewForPlay.setOnClickListener {
            stopValidation = true
            pauseValidation = true
            nextButtonValidation= true
            previousButtonValidation= true
            if (playValidation) {
                //binding.imageViewForSong0.alpha = 1f

                //Image showing is here.
                var currentImage2= currentImage- 1
                val idCurrentImageStringToShow= "imageViewForSong$currentImage2"
                //Converting string id into integer address associated with it.
                val idCurrentImageIntegerToShow= this.resources.getIdentifier(idCurrentImageStringToShow, "id", packageName)
                image= findViewById(idCurrentImageIntegerToShow)
                image.alpha= 1f

                mediaPlayer.start()
                binding.textViewForSongTitle.text= musicTittleArray[traversing]
                binding.imageViewForPause.alpha = 1f
                binding.imageViewForStop.alpha = 1f
                playValidation = false
            } else {
                Toast.makeText(this, "Already pressed play button", Toast.LENGTH_SHORT).show()
            }

        }
        binding.imageViewForPause.setOnClickListener {
            stopValidation = true
            playValidation = true
            nextButtonValidation= true
            previousButtonValidation= true
            if (pauseValidation) {
                //binding.imageViewForSong0.alpha = 0f

                //Image hiding is here.
                var currentImage1= currentImage- 1
                val idCurrentImage= "imageViewForSong$currentImage1"
                //Converting string id into integer address associated with it.
                val idCurrentImageInteger= this.resources.getIdentifier(idCurrentImage, "id", packageName)
                image= findViewById(idCurrentImageInteger)
                image.alpha= 0f

                //binding.imageViewForMusicLogo.alpha = 1f
                mediaPlayer.pause()
                pauseValidation = false
            } else {
                Toast.makeText(this, "Already pressed pause button", Toast.LENGTH_SHORT).show()
            }
                binding.imageViewForMusicLogo.alpha = 1f

        }
        binding.imageViewForStop.setOnClickListener {
            playValidation = true
            pauseValidation = true
            nextButtonValidation= true
            previousButtonValidation= true
            if (stopValidation) {
                //mediaPlayer.stop()
                //mediaPlayer.reset()
                //mediaPlayer.release()
                //stopValidation = false
                mediaPlayer.stop()
                createMusicPlayer(musicLocationArray[traversing])
                mediaPlayer.start()
                //traversing= 0

            } else {
                Toast.makeText(this, "Already Playing 1st Song", Toast.LENGTH_SHORT).show()
            }

        }

        //seekbar
        //When user changes the time stamp of music, reflect that change.
        binding.seekBar.max = totalTime
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {  }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mediaPlayer.start()
            }

        })

        //Changing the seekBar position based on the music.
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (exception: Exception) {
                    binding.seekBar.progress = 0
                }

            }

        }, 0)

        binding.imageViewForNext.setOnClickListener {
            binding.imageViewForPause.alpha = 1f
            binding.imageViewForStop.alpha = 1f

            //Image hiding is here.
            var currentImage3= currentImage- 1
            val idCurrentImage= "imageViewForSong$currentImage3"
            //Converting string id into integer address associated with it.
            val idCurrentImageInteger= this.resources.getIdentifier(idCurrentImage, "id", packageName)
            image= findViewById(idCurrentImageInteger)
            image.alpha= 0f

            //Image showing is here.
            //currentImage += 1
            val idCurrentImageStringToShow= "imageViewForSong$currentImage"
            //Converting string id into integer address associated with it.
            val idCurrentImageIntegerToShow= this.resources.getIdentifier(idCurrentImageStringToShow, "id", packageName)
            image= findViewById(idCurrentImageIntegerToShow)
            image.alpha= 1f

            //Changing the Next song and Song Title.
            if (nextButtonValidation) {
                //Toast.makeText(this, "Next song", Toast.LENGTH_SHORT).show()
                mediaPlayer.stop()
                if (traversing == 0) {
                    createMusicPlayer(musicLocationArray[traversing+ 1])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text= musicTittleArray[1]
                    traversing++
                    currentImage++
                } else if (traversing == 1) {
                    createMusicPlayer(musicLocationArray[traversing+ 1])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text= musicTittleArray[2]
                    traversing++
                    currentImage++
                }else if (traversing == 2) {
                    createMusicPlayer(musicLocationArray[traversing + 1])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text = musicTittleArray[3]
                    traversing++
                    currentImage++
                } else if (traversing == 3) {
                    createMusicPlayer(musicLocationArray[traversing+ 1])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text= musicTittleArray[4]
                    traversing++
                    nextButtonValidation = false
                    previousButtonValidation= true
                    pauseValidation= true
                    currentImage= 4
                }
            } else {
                Toast.makeText(this, "No next song", Toast.LENGTH_SHORT).show()
                currentImage= 4
            }


        }
        binding.imageViewForPrevious.setOnClickListener {
            binding.imageViewForPause.alpha = 1f
            binding.imageViewForStop.alpha = 1f

            //Image hiding is here.
            val idCurrentImage= "imageViewForSong$currentImage"
            //Converting string id into integer address associated with it.
            val idCurrentImageInteger= this.resources.getIdentifier(idCurrentImage, "id", packageName)
            image= findViewById(idCurrentImageInteger)
            image.alpha= 0f

            //Image showing is here.
            currentImage -= 1
            val idCurrentImageStringToShow= "imageViewForSong$currentImage"
            //Converting string id into integer address associated with it.
            val idCurrentImageIntegerToShow= this.resources.getIdentifier(idCurrentImageStringToShow, "id", packageName)
            image= findViewById(idCurrentImageIntegerToShow)
            image.alpha= 1f

            //Changing the Previous song and Song Title.
            if (previousButtonValidation) {
                //Toast.makeText(this, "Previous song", Toast.LENGTH_SHORT).show()
                mediaPlayer.stop()
                if (traversing == 4) {
                    createMusicPlayer(musicLocationArray[traversing- 1])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text= musicTittleArray[3]
                    traversing--
                } else if (traversing == 3) {
                    createMusicPlayer(musicLocationArray[traversing- 1])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text= musicTittleArray[2]
                    traversing--
                }else if (traversing == 2) {
                    createMusicPlayer(musicLocationArray[traversing- 1])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text= musicTittleArray[1]
                    traversing--
                } else if (traversing == 1) {
                    createMusicPlayer(musicLocationArray[0])
                    mediaPlayer.start()
                    binding.textViewForSongTitle.text= musicTittleArray[0]
                    traversing= 0
                    previousButtonValidation= false
                    nextButtonValidation= true
                    pauseValidation= true
                    currentImage= 1
                }
            } else {
                Toast.makeText(this, "No previous song", Toast.LENGTH_SHORT).show()
                currentImage= 1
            }

        }

    }

    private fun createMusicPlayer(musicLocation: Int) {
        //Initialization of musicPlayer.
        mediaPlayer = MediaPlayer.create(this, musicLocation)
        mediaPlayer.setVolume(1f, 1f)
        totalTime = mediaPlayer.duration
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

}