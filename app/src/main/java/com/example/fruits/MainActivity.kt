package com.example.fruits

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val items = ArrayList<Fruit>()
    private val adapter: ListAdapter by lazy {
        ListAdapter(items)
    }

    private fun initRecyclerView() {
        recycler.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        getAllData()

        progressBar.visibility = View.VISIBLE



        btn_busca.setOnClickListener {

        }

        card.setOnClickListener {
            val intent = Intent(this, FruitDetail::class.java)
            startActivity(intent)
        }

        fruit_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

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


    fun getAllData() {
        val result = Api.getInstance().getFruits()
        Log.d("URL", result.toString())
        result.enqueue(object : Callback<FindResult> {

            override fun onFailure(call: Call<FindResult>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<FindResult>,
                response: Response<FindResult>) {
                progressBar.visibility = View.GONE
                val result: FindResult = response.body()!!

                for (item in result.list ) {
                    adapter.addItem(Fruit(item.tfvname, item.botname, item.othname, item.imageurl))
                }
            }
        })
    }
}
