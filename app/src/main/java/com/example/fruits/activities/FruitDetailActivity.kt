package com.example.fruits.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fruits.R
import com.example.fruits.requests.Api
import com.example.fruits.requests.endpoints.IFruitDetailService
import com.example.fruits.requests.entity.DetailResults
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_fruit_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FruitDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_detail)

        supportActionBar?.title = getString(R.string.detail_title)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val intent = intent
        val name = intent.getStringExtra("name")

        progressBar_detail.visibility = View.VISIBLE

        if (isDeviceConnected())
            getData(name)
        else
            Snackbar.make(findViewById(R.id.detail_layout), R.string.no_network, Snackbar.LENGTH_LONG).show()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    private fun isDeviceConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    private fun getData(parameterName: String) {
        val result = Api.getInstance().create(IFruitDetailService::class.java).getFruitDetail(parameterName)
        result.enqueue(object : Callback<DetailResults> {

            override fun onFailure(call: Call<DetailResults>, t: Throwable) {
                progressBar_detail.visibility = View.GONE
                Toast.makeText(this@FruitDetailActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<DetailResults>, response: Response<DetailResults>) {
                val result: DetailResults = response.body()!!

                for (item in result.list) {
                    name_fruit.text = item.tfvname
                    botname_detail.text = item.botname
                    othname_detail.text = item.othname
                    Glide.with(this@FruitDetailActivity)
                        .load(item.imageurl)
                        .into(fruit_detail_img)
                    desc_detail.text = item.description
                }
                progressBar_detail.visibility = View.GONE
            }
        })
    }
}
