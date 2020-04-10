package com.example.crudvolleykotlin.Util

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import java.util.*

var instance : AppController? = null
var mRequestQueue: RequestQueue? = null

val TAG : String = AppController::class.java.simpleName

class AppController : Application(){



    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    // @Synchronized
    companion object{
        @Synchronized
        fun getInstance() : AppController? {
            return instance
        }}

    fun getRequestQueue() : RequestQueue?{
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return mRequestQueue
    }

    fun <T> addToRequestQueue(req : Request<T>, tag : String){

        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        getRequestQueue()!!.add(req)

    }

    fun <T> addToRequestQueue (req : Request<T>){

        req.tag = TAG
        getRequestQueue()!!.add(req)

    }

    fun cancleAllRequest(req: Any){

        if(mRequestQueue != null){

            mRequestQueue!!.cancelAll(req)

        }

    }

}