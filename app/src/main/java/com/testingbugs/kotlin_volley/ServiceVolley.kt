package com.testingbugs.kotlinrecyclerview

import android.util.Base64
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class ServiceVolley : ServiceInterface {
    val TAG = ServiceVolley::class.java.simpleName
    val basePath = "http://182.76.83.115/soundsignature/index.php/"

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {


        val jsonObjReq = object : JsonObjectRequest(Method.GET, basePath + path, null,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/post request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
             /*   String credentials = "admin" + ":" + "1234";
                String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);*/
                var auth:String
                var credentials="admin"+":"+"1234"
                auth="Basic"+Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
                headers.put("Authorization",auth);
                headers.put("X-API-KEY", "anonymous")
                headers.put("Content-Type", "application/x-www-form-urlencoded")
                return headers
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}