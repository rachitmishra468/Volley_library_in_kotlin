package com.testingbugs.kotlinrecyclerview

import org.json.JSONObject

interface ServiceInterface {

    //fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)


    fun post(path: String,params:JSONObject,completionHandler :(responce :JSONObject?)->Unit)
}