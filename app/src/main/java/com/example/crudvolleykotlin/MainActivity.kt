package com.example.crudvolleykotlin

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonArrayRequest
import com.example.crudvolleykotlin.Adapter.AdapterData
import com.example.crudvolleykotlin.Model.ModelData
import com.example.crudvolleykotlin.Util.AppController
import com.example.crudvolleykotlin.Util.ServerApi
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: AdapterData
    private lateinit var mManager: RecyclerView.LayoutManager
    private lateinit var mItems : List<ModelData>
    private lateinit var data : JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recyclerViewTemp)
        val btnInsert = findViewById<Button>(R.id.btn_insert)
        val btnDelete = findViewById<Button>(R.id.btn_delete)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        progressBar.visibility = View.INVISIBLE
        mItems = ArrayList<ModelData>()

        loadJson()

        mManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
        mRecyclerView.layoutManager = mManager
        mAdapter = AdapterData(this, mItems)
        mRecyclerView.adapter = mAdapter

        btnInsert.setOnClickListener { v->
            val intent = Intent(this, InsertData::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener { v ->
            val intent = Intent(this, Delete::class.java)
            startActivity(intent)
        }

    }

    private fun loadJson()
    {
        progressbar.visibility = View.VISIBLE

        val reqData = JsonArrayRequest(Request.Method.POST, ServerApi.URL_DATA, null, Listener<JSONArray>{ response ->

            progressbar.visibility = View.INVISIBLE
            Log.d("volley", "response : ${response.toString()}")

            for (i in 0..(response.length() - 1)) {
                data = response.getJSONObject(i)
                val md = ModelData()
                md.npm = data.getString("npm")
                md.nama = data.getString("nama")
                md.prodi = data.getString("prodi")
                md.fakultas = data.getString("fakultas")
                (mItems as ArrayList).add(md)
            }
            mAdapter.notifyDataSetChanged()

        }, Response.ErrorListener { error ->
            progressbar.visibility = View.INVISIBLE
            Log.d("volley", "Error : ${error.message}")
        })
        AppController.getInstance()!!.addToRequestQueue(reqData)
    }
}
