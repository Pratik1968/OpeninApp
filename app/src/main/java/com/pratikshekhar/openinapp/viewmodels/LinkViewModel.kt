package com.pratikshekhar.openinapp.viewmodels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikshekhar.openinapp.database.AuthorizationToken
import com.pratikshekhar.openinapp.model.Api_Response
import com.pratikshekhar.openinapp.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinkViewModel@Inject constructor(private val respository:DataRepository):ViewModel() {
    val data:StateFlow<Api_Response>
        get() = respository.data
    fun getData(token:String){

        viewModelScope.launch {
respository.getData(token)

        }
    }
}