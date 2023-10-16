package com.luthfi.appinfogame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvGames: RecyclerView
    private val list = ArrayList<Game>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGames = findViewById(R.id.rv_games)
        rvGames.setHasFixedSize(true)

        list.addAll(getListGames())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListGames(): ArrayList<Game> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataDescriptionAddition = resources.getStringArray(R.array.data_description_addition)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listGame = ArrayList<Game>()
        for (i in dataName.indices) {
            val game = Game(dataName[i], dataDescription[i], dataDescriptionAddition[i], dataPhoto.getResourceId(i, -1))
            listGame.add(game)
        }
        return listGame
    }

    private fun showRecyclerList() {
        rvGames.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        rvGames.adapter = listGameAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_about_page -> {
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}