package com.example.tp8exo1adn

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {



    private lateinit var buttonDownload: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textStatus: TextView

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDownload = findViewById(R.id.button_download)
        progressBar = findViewById(R.id.progress_bar)
        textStatus = findViewById(R.id.text_status)

        buttonDownload.setOnClickListener {
            startDownload()
        }
    }

    private fun startDownload() {
        buttonDownload.isEnabled = false
        progressBar.progress = 0
        textStatus.text = "Téléchargement en cours : 0%"

        Thread {
            for (i in 1..10) {
                Thread.sleep(700) // Simule une étape de téléchargement

                val progress = i * 10
                handler.post {
                    progressBar.progress = progress
                    textStatus.text = "Téléchargement en cours : $progress%"
                }
            }

            handler.post {
                textStatus.text = "Téléchargement terminé !"
                buttonDownload.isEnabled = true
            }
        }.start()
        val imageView = findViewById<ImageView>(R.id.image_view)

        val animator = ObjectAnimator.ofFloat(imageView, "translationX", -500f, 500f)
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()


//        val imageView = findViewById<ImageView>(R.id.image_view)
//
//        val moveX = ObjectAnimator.ofFloat(imageView, "translationX", -500f, 500f)
//        val rotate = ObjectAnimator.ofFloat(imageView, "rotation", -360f, 360f)
//        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", -1f, 1.5f)
//        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", -1f, 1.5f)
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playTogether(moveX, rotate, scaleX, scaleY)
//        animatorSet.duration = 2000
//        animatorSet.start()


    }

}
