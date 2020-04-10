package com.example.crudvolleykotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import org.json.JSONObject

class Delete : AppCompatActivity() {

    lateinit var deleteID : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        deleteID = findViewById(R.id.npm_param)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        progressBar.visibility = View.INVISIBLE
        val btnDelete = findViewById(R.id.btn_delete) as Button

        btnDelete.setOnClickListener { v ->
            deleteData()
        }

    }

    private fun deleteData(){

        progressbar.visibility = View.VISIBLE

        val delreg = object : StringRequest(Request.Method. POST, ServerApi.URL_DELETE, Response.Listener<String> {response ->

            progressbar.visibility = View.INVISIBLE
            val res = JSONObject(response)
            Toast.makeText(this@Delete,"pesan : ${res.getString("message")}",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@Delete,MainActivity::class.java))

        }, Response.ErrorListener { error ->

            progressbar.visibility = View.INVISIBLE
            Log.d("volley", "error : ${error.toString()}")
            Toast.makeText(this@Delete, "pesan : Gagal Menghapus Data ",Toast.LENGTH_LONG).show()

        }){
            override fun getParams() : Map<String, String>{
                val map = HashMap<String, String>()
                map.put("npm",deleteID.text.toString())
                return map
            }
        }
        AppController.getInstance()!!.addToRequestQueue(delreg)
    }

}
