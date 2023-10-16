package com.luthfi.appinfogame

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailPage : AppCompatActivity() {
    companion object {
        const val key_game = "key_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)
        val tvDescriptionAddition: TextView = findViewById(R.id.tv_detail_description_addition)

        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener{
            shareApp()
        }

        val dataHero = if (Build.VERSION.SDK_INT >= 34) {
            intent.getParcelableExtra<Game>(key_game, Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Game>(key_game)
        }

        if (dataHero != null) {
            tvDetailName.text = dataHero.name
            tvDetailDescription.text = dataHero.description
            ivDetailPhoto.setImageResource(dataHero.photo)
            tvDescriptionAddition.text = dataHero.descriptionAddition
        }
    }

    private fun shareApp() {
        val sendMsg: String = "Dapatkan Info Game paling lengkap dan tentunya up-to-date!. Klik link berikut: " + "https://play.google.com/store/apps/details?id=com.luthfi.appinfogame"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, sendMsg)
        intent.type = "text/plain"
        startActivity(intent)
    }
}