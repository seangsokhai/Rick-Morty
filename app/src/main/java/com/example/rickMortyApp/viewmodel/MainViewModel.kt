package com.example.rickMortyApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickMortyApp.network.*
import com.example.rickMortyApp.repository.Repository
import com.example.rickMortyApp.ulti.ScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel() {
    private val _characterLiveData = MutableLiveData<ScreenState<List<Character>?>> ()
    val characterLiveData: LiveData<ScreenState<List<Character>?>>
        get() = _characterLiveData

    private val _locationLiveData = MutableLiveData<ScreenState<List<LocationData>?>> ()
    val locationLiveData: LiveData<ScreenState<List<LocationData>?>>
        get() = _locationLiveData

    private val _episodeLiveData = MutableLiveData<ScreenState<List<EpisodeData>?>> ()
    val episodeLiveData: LiveData<ScreenState<List<EpisodeData>?>>
        get() = _episodeLiveData

    init {
        fetchCharacter()
        fetchLocation()
        fetchEpisode()
    }

    private fun fetchCharacter(){
        val client = repository.getCharacter("1")
        println("character ${client.request()}")
        _characterLiveData.postValue(ScreenState.Loading(null))
        client.enqueue(object : Callback<CharacterResponse>{
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {1
                throw(Error("Character: ${t.message.toString()}"))
                _characterLiveData.postValue(ScreenState.Error(t.message.toString(),null))
            }
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful){
                    _characterLiveData.postValue(ScreenState.Success(response.body()?.results))
                } else {
                    _characterLiveData.postValue(ScreenState.Error(response.code().toString()))
                }
            }
        })
    }
    private fun fetchLocation(){
        val client = repository.getLocation("1")
        println("location ${client.request()}")
        _locationLiveData.postValue(ScreenState.Loading(null))
        client.enqueue(object : Callback<LocationResponse>{
            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {

                _locationLiveData.postValue(ScreenState.Error(t.message.toString(),null))
                throw(Error("Location: ${t.message.toString()}"))
            }
            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                if (response.isSuccessful){
                    _locationLiveData.postValue(ScreenState.Success(response.body()?.results))
                } else {
                    _locationLiveData.postValue(ScreenState.Error(response.code().toString()))
                }
            }
        })
    }
    private fun fetchEpisode(){
        val client = repository.getEpisode("1")
        println("hallo ${client.request()}")
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
                print("hallo25636 $response")
                if (response.isSuccessful){
                    _episodeLiveData.postValue(ScreenState.Success(response.body()?.results))
                } else {
                    _episodeLiveData.postValue(ScreenState.Error(response.code().toString()))
                }
            }
        })
    }
}