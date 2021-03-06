package com.example.rickMortyApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickMortyApp.network.*
import com.example.rickMortyApp.repository.Repository
import com.example.rickMortyApp.ulti.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject



//class LocationViewModel(private val repository: Repository = Repository(ApiClient.apiService))  : ViewModel() {

@HiltViewModel
class LocationViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    suspend fun fetchLocation(page: String = "1") = repo.getLocation(page)
    suspend fun fetchFilterLocation(page: String, type: String, dimension: String) = repo.getFilterLocation(page, type, dimension)
}

//private val _locationLiveData = MutableLiveData<ScreenState<List<LocationData>?>>()
//    val locationLiveData: LiveData<ScreenState<List<LocationData>?>>
//        get() = _locationLiveData
//
//    init {
//        fetchLocation()
//    }
//    private fun fetchLocation(){
//        viewModelScope.launch {
//            val client = repository.getLocation("1")
//            println("location ${client.request()}")
//            _locationLiveData.postValue(ScreenState.Loading(null))
//            client.enqueue(object : Callback<LocationResponse> {
//                override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
//
//                    _locationLiveData.postValue(ScreenState.Error(t.message.toString(),null))
//                    throw(Error("Location: ${t.message.toString()}"))
//                }
//                override fun onResponse(
//                    call: Call<LocationResponse>,
//                    response: Response<LocationResponse>
//                ) {
//                    if (response.isSuccessful){
//                        _locationLiveData.postValue(ScreenState.Success(response.body()?.results))
//                    } else {
//                        _locationLiveData.postValue(ScreenState.Error(response.code().toString()))
//                    }
//                }
//            })
//
//        }
//
//    }