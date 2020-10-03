package com.example.fruits.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fruits.R
import kotlinx.android.synthetic.main.activity_fruit_detail.*


class FruitDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_detail)

        supportActionBar?.title = getString(R.string.detail_title)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val intent = intent
        val name = intent.getStringExtra("name")
        val img = intent.getStringExtra("image")
        val othname = intent.getStringExtra("othname")
        val botname = intent.getStringExtra("botname")

        Glide.with(this)
            .load(img)
            .into(fruit_detail_img)

        name_fruit.text = name
        othname_detail.text = othname
        botname_detail.text = botname
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

}
