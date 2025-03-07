package com.example.uxassignment1.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uxassignment1.R
import com.example.uxassignment1.databinding.ActivityViewAnimatorBinding
import kotlin.math.sin

class ViewAnimatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewAnimatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewAnimatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button click listeners for different animations
        binding.btnNumberAnimation.setOnClickListener { numberAnimation() }
        binding.btnTranslation.setOnClickListener { translationAnimation() }
        binding.btnColor.setOnClickListener { colorAnimation() }
        binding.btnWave.setOnClickListener { waveAnimation() }
        binding.btnFlip.setOnClickListener { flipAnimation() }
        binding.btnTypewriter.setOnClickListener { typewriterAnimation() }
        binding.btnShimmer.setOnClickListener { shimmerEffect() }
        binding.btnParticle.setOnClickListener { particleEffect() }
        binding.btnBounce.setOnClickListener { bounceEffect() }
        binding.btnCircularProgress.setOnClickListener { circularProgressAnimation() }
        binding.btnButtonPress.setOnClickListener { buttonPressEffect() }
        binding.btnGradientBackground.setOnClickListener { gradientBackgroundAnimation() }
        binding.btnReset.setOnClickListener { resetAnimations() }
    }

    private fun numberAnimation() {
        val animator = ValueAnimator.ofInt(0, 100)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            binding.textView.text = animation.animatedValue.toString()
        }
        animator.start()
    }

    private fun translationAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 500f)
        animator.duration = 2000
        animator.addUpdateListener { animation ->
            binding.animatedView.translationX = animation.animatedValue as Float
        }
        animator.start()
    }

    private fun colorAnimation() {
        val animator = ValueAnimator.ofObject(ArgbEvaluator(), Color.RED, Color.BLUE)
        animator.duration = 2000
        animator.addUpdateListener { animation ->
            binding.animatedView.setBackgroundColor(animation.animatedValue as Int)
        }
        animator.start()
    }

    private fun waveAnimation() {
        val animator = ValueAnimator.ofFloat(0f, (2 * Math.PI).toFloat())
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            val y = (sin(value.toDouble()) * 100).toFloat()
            binding.animatedView.translationY = y
        }
        animator.start()
    }

    private fun flipAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 180f)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            binding.animatedView.rotationY = animation.animatedValue as Float
        }
        animator.start()
    }

    private fun typewriterAnimation() {
        val text = "Hello, Android!"
        val animator = ValueAnimator.ofInt(0, text.length)
        animator.duration = 2000
        animator.addUpdateListener { animation ->
            val length = animation.animatedValue as Int
            binding.textView.text = text.substring(0, length)
        }
        animator.start()
    }

    private fun shimmerEffect() {
        val animator = ValueAnimator.ofFloat(-binding.animatedView.width.toFloat(), binding.animatedView.width.toFloat())
        animator.duration = 1500
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            binding.animatedView.translationX = value
        }
        animator.start()
    }

    private fun particleEffect() {
        for (i in 0..10) {
            val animator = ValueAnimator.ofFloat(0f, 500f)
            animator.duration = 2000
            animator.startDelay = (i * 100).toLong()
            animator.addUpdateListener { animation ->
                binding.animatedView.translationY = animation.animatedValue as Float
            }
            animator.start()
        }
    }

    private fun bounceEffect() {
        val animator = ValueAnimator.ofFloat(0f, 300f, 0f)
        animator.duration = 1500
        animator.interpolator = BounceInterpolator()
        animator.addUpdateListener { animation ->
            binding.animatedView.translationY = animation.animatedValue as Float
        }
        animator.start()
    }

    private fun circularProgressAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.duration = 2000
        animator.addUpdateListener { animation ->
            binding.progressBar.rotation = animation.animatedValue as Float
        }
        animator.start()
    }

    private fun buttonPressEffect() {
        val animator = ValueAnimator.ofFloat(1f, 0.9f, 1f)
        animator.duration = 300
        animator.interpolator = OvershootInterpolator()
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            binding.animatedView.scaleX = value
            binding.animatedView.scaleY = value
        }
        animator.start()
    }

    private fun gradientBackgroundAnimation() {
        val animator = ValueAnimator.ofObject(ArgbEvaluator(), Color.RED, Color.BLUE, Color.GREEN)
        animator.duration = 3000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.addUpdateListener { animation ->
            binding.root.setBackgroundColor(animation.animatedValue as Int)
        }
        animator.start()
    }

    private fun resetAnimations() {
        binding.animatedView.apply {
            translationX = 0f
            translationY = 0f
            rotationY = 0f
            scaleX = 1f
            scaleY = 1f
            setBackgroundColor(Color.LTGRAY)
        }
        binding.textView.text = "Animation Example"
        binding.progressBar.rotation = 0f
        binding.root.setBackgroundColor(Color.WHITE)
    }

}