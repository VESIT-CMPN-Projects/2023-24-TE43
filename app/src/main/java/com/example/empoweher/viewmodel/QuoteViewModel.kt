package com.example.empoweher.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.empoweher.model.QuoteRepo
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json
import org.json.JSONObject

class QuoteViewModel:ViewModel() {

    val _repo:MutableState<QuoteRepo> = mutableStateOf(
        QuoteRepo(
            quotes = listOf()
        )
    )

    val repo:MutableState<QuoteRepo> =_repo
    fun getUsers(){
        val url = "https://zenquotes.io/api/random/g"

        val header: HashMap<String, String> = hashMapOf()
        Fuel.get(url).header(header).responseJson{ request, response, result ->

            when(result){
                is Result.Failure -> {
                    val ex = result.getException()
                    if(ex.response.statusCode == 404){
                        // do something if got 404
                        var tmp = QuoteRepo(quotes = listOf())
                        _repo.value = tmp
                    }
                }

                is Result.Success -> {

//                    var tmp = UserRepositories(users = listOf())
//                    val tmp = Json.decodeFromString<QuoteRepo>(result.get().toString())
//
//                    val obj = JSONObject()
//                    obj.put()
//
//                    Log.d("API",tmp.toString())

//                    _repo.value = tmp

                }

                else -> {
                    var tmp = QuoteRepo(quotes = listOf())
                    _repo.value = tmp
                }
            }

        }
    }


}