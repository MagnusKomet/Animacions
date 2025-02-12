package com.example.animacions

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animacions.databinding.ActivityBallBinding

class BallActivity : AppCompatActivity() {

    lateinit var binding: ActivityBallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ballImageView = binding.ballImage

        // Al fer click s'obra el MainActivity amb una transició d'opacitat.
        ballImageView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        // Animacions de la bola
        ballImageView.post {
            // Agafem el tamany de la pantalla i de la bola
            val screenWidth = ballImageView.rootView.width.toFloat()
            val screenHeight = ballImageView.rootView.height.toFloat()
            val imageWidth = ballImageView.width.toFloat()
            val imageHeight = ballImageView.height.toFloat()

            // Preparem totes les animacions i el seu ordre
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(

                // Moviment per les parets
                ObjectAnimator.ofFloat(ballImageView, "translationX", 0f, screenWidth - imageWidth),
                ObjectAnimator.ofFloat(ballImageView, "translationY", 0f, screenHeight - imageHeight),
                ObjectAnimator.ofFloat(ballImageView, "translationX", screenWidth - imageWidth, 0f),
                ObjectAnimator.ofFloat(ballImageView, "translationY", screenHeight - imageHeight, 0f),

                // Moviment al centre
                ObjectAnimator.ofFloat(ballImageView, "translationX", (screenWidth - imageWidth) / 2),
                ObjectAnimator.ofFloat(ballImageView, "translationY", (screenHeight - imageHeight) / 2)
            )

            //Seleccionem la duració i començem l'animació
            animatorSet.duration = 1000
            animatorSet.start()
        }

    }
}