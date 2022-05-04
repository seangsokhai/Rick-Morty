package com.example.rickMortyApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickMortyApp.network.*
import com.example.rickMortyApp.repository.Repository
import com.example.rickMortyApp.ulti.ScreenState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EpisodeViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private val _episodeLiveData = MutableLiveData<ScreenState<List<EpisodeData>?>> ()
    val episodeLiveData: LiveData<ScreenState<List<EpisodeData>?>>
        get() = _episodeLiveData

    init {
        fetchEpisode()
    }

    private fun fetchEpisode(){

        viewModelScope.launch {
            val client = repository.getEpisode("1")

            _episodeLiveData.postValue(ScreenState.Loading(null))
            client.enqueue(object : Callback<EpisodeResponse>{
                override fun onFailure(call: Call<EpisodeResponse>, t: Throwable) {
                    throw(Error("Episode: ${t.message.toString()}"))
                    _episodeLiveData.postValue(ScreenState.Error(t.message.toString(),null))
                }
                override fun onResponse(
                    call: Call<EpisodeResponse>,
                    response: Response<EpisodeResponse>
                ) {
                    if (response.isSuccessful){
                        _episodeLiveData.postValue(ScreenState.Success(response.body()?.results))
                    } else {
                        _episodeLiveData.postValue(ScreenState.Error(response.code().toString()))
                    }
                }
            })
        }

    }


}