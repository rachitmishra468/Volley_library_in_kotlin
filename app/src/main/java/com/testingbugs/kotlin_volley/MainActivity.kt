package com.testingbugs.kotlin_volley

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import com.example.admin.recyclerviewkotlin.MyAndroidAdapter
import com.example.admin.recyclerviewkotlin.MyAndroidOS
import com.testingbugs.kotlinrecyclerview.APIController
import com.testingbugs.kotlinrecyclerview.ServiceVolley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById(R.id.rvAndroidVersions) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)

        val service = ServiceVolley()
        val apiController = APIController(service)
        val alMyAndroidOS = ArrayList<MyAndroidOS>()
        var button =findViewById(R.id.button) as Button




        button.setOnClickListener(){

            val path = "api/v1/faqs"
            val params = JSONObject()



            apiController.post(path, params)

            { response ->
                var responce_data: String = response.toString()
                Log.d("TAG@123",responce_data)


                var obj = JSONObject(responce_data)


                var jsonArray = obj.getJSONArray("faqs")



                for (jsonIndex in 0..(jsonArray.length() - 1)) {

                    var jsonobject=jsonArray.getJSONObject(jsonIndex)


                    alMyAndroidOS.add(MyAndroidOS(0,jsonobject.getString("answer"), jsonobject.getString("question")))

                    Log.d("JSON", jsonArray.getJSONObject(jsonIndex).getString("question"))
                }


                recyclerView.adapter = MyAndroidAdapter(alMyAndroidOS){
                    //Anko library has its own definition of toast which we have addded in build.gradle
                    //A toast that displays the name of OS which you clicked on
                }





            }


        }



    }
}
