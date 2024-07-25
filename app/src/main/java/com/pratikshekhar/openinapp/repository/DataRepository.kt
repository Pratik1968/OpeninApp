package com.pratikshekhar.openinapp.repository

import android.util.Log
import android.widget.Toast
import com.pratikshekhar.openinapp.api.DataApi
import com.pratikshekhar.openinapp.model.Api_Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import java.security.AccessController.getContext
import javax.inject.Inject


class DataRepository @Inject constructor(private val dataApi: DataApi) {
    private val _data:MutableStateFlow<Api_Response> = MutableStateFlow<Api_Response>(Api_Response())
     val data:StateFlow<Api_Response>
        get() = _data
    suspend fun getData(token:String){
        val response = dataApi.getData(token)
        if(response.isSuccessful && response.body()!=null ){
            Log.d("response",response.body().toString())
_data.emit(response.body()!!)
        }else{

            Log.d("responseError",response.isSuccessful.toString())

        }
    }
    suspend fun postData(token:String){
        val response = dataApi.postData(token)
        if(response.isSuccessful && response.body()!=null ){
Log.d("Post","Success")
        }
    }
}