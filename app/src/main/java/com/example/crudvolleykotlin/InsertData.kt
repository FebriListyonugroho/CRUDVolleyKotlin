package com.example.crudvolleykotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.crudvolleykotlin.Util.AppController
import com.example.crudvolleykotlin.Util.ServerApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_row.*
import org.json.JSONObject

class InsertData : AppCompatActivity() {

    lateinit var npm : EditText
    lateinit var nama : EditText
    lateinit var prodi : EditText
    lateinit var fakultas : EditText
    lateinit var btnsimpan : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)

        ///mendapat data untuk diupdate
        val data = intent
        val update = data.getIntExtra("update",0)
        val intent_npm = data.getStringExtra("npm")
        val intent_nama = data.getStringExtra("nama")
        val intent_prodi = data.getStringExtra("prodi")
        val intent_fakultas = data.getStringExtra("fakultas")
        //selesai Update


        //insertData
        npm = findViewById(R.id.inp_npm)
        nama = findViewById(R.id.inp_nama)
        prodi = findViewById(R.id.inp_prodi)
        fakultas = findViewById(R.id.inp_fakultas)
        val btnbatal = findViewById<Button>(R.id.btn_cancel)
        btnsimpan = findViewById(R.id.btn_simpan)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        progressBar.visibility = View.INVISIBLE

        //kondisi update
        if (update == 1){
            btnsimpan.setText("Update Data")
            npm.setText(intent_npm)
            npm.visibility = View.GONE
            nama.setText(intent_nama)
            prodi.setText(intent_prodi)
            fakultas.setText(intent_fakultas)

        }

        btnsimpan.setOnClickListener { v ->
            if (update == 1){
                updatedata()
            }else {
                simpanData()
            }
        }
        btnbatal.setOnClickListener { v ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun updatedata(){

        progressbar.visibility = View.VISIBLE

        val updateReq = object : StringRequest(Request.Method.POST,ServerApi.URL_UPDATE,Response.Listener<String> { response ->

            progressbar.visibility = View.INVISIBLE
            val res = JSONObject(response)
            res.getString("message")
            Toast.makeText(this@InsertData, "pesan : ${res.getString("message")}",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))

        }, Response.ErrorListener { error ->

            progressbar.visibility = View.INVISIBLE
            Toast.makeText(this@InsertData, "pesan : ${error.toString()}",Toast.LENGTH_LONG).show()

        }){

            override fun getParams() : HashMap<String, String>{
                val map = HashMap<String, String>()
                map.put("npm",npm.text.toString())
                map.put("nama",nama.text.toString())
                map.put("prodi",prodi.text.toString())
                map.put("fakultas",fakultas.text.toString())
                return map
            }
        }
        AppController.getInstance()!!.addToRequestQueue(updateReq)

    }

    private fun simpanData(){

        progressbar.visibility = View.VISIBLE

        val sendData = object : StringRequest(Request.Method.POST,ServerApi.URL_INSERT,Response.Listener<String> { response ->

            progressbar.visibility = View.INVISIBLE
            val res = JSONObject(response)
            res.getString("message")
            Toast.makeText(this@InsertData, "pesan : ${res.getString("message")}",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))

        }, Response.ErrorListener { error ->

            progressbar.visibility = View.INVISIBLE
            Toast.makeText(this@InsertData, "pesan : ${error.toString()}",Toast.LENGTH_LONG).show()

        }){

            override fun getParams() : HashMap<String, String>{
                val map = HashMap<String, String>()
                map.put("npm",npm.text.toString())
                map.put("nama",nama.text.toString())
                map.put("prodi",prodi.text.toString())
                map.put("fakultas",fakultas.text.toString())
                return map
            }
        }
        AppController.getInstance()!!.addToRequestQueue(sendData)
    }
}
