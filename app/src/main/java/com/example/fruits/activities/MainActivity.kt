package com.example.fruits.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fruits.R
import com.example.fruits.adapters.ListAdapter
import com.example.fruits.requests.Api
import com.example.fruits.requests.endpoints.IFruitService
import com.example.fruits.requests.entity.FindResult
import com.example.fruits.requests.entity.Fruit
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val items = ArrayList<Fruit>()
    private val adapter: ListAdapter by lazy {
        ListAdapter(items, this)
    }

    private fun initRecyclerView() {
        recycler.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        if (isDeviceConnected())
            getData("all")
        else
            Snackbar.make(findViewById(R.id.main_layout), R.string.no_network, Snackbar.LENGTH_LONG).show()

        progressBar.visibility = View.VISIBLE

        fruit_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isDeviceConnected()) {
                    if (fruit_search.text.toString().isEmpty()) {
                        getData("all")
                        initRecyclerView()
                    } else {
                        getData(fruit_search.text.toString())
                        val position: Int = findPosition(items, fruit_search.text.toString())
                        if (position != -1) {
                            val list = ArrayList<Fruit>()
                            list.add(items[position])
                            initRecyclerView()

                            recycler.scrollToPosition(position)
                        } else {
                            return
                        }
                    }
                } else {
                    Snackbar.make(
                        findViewById(R.id.main_layout), R.string.no_network, Snackbar.LENGTH_LONG).show()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
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

    private fun findPosition(list: List<Fruit>, name: String?): Int {
        for (i in list.indices) {
            if (list[i].tfvname.toLowerCase() == name)
                return i
        }
        return -1
    }

    private fun getData(parameterName: String) {
        val result = Api.getInstance().create(IFruitService::class.java).getFruits(parameterName)
        result.enqueue(object : Callback<FindResult> {

            override fun onFailure(call: Call<FindResult>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<FindResult>, response: Response<FindResult>) {
                val result: FindResult = response.body()!!

                for (item in result.list) {
                    adapter.addItem(Fruit(item.tfvname, item.botname, item.othname, item.imageurl))
                }
                progressBar.visibility = View.GONE
            }
        })
    }
}
