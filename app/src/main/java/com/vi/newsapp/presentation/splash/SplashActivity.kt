package com.vi.newsapp.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vi.newsapp.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        toNextSection()
    }

    private fun toNextSection() {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        finish()
    }

}