package com.example.rickMortyApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.rickMortyApp.network.ApiClient
import com.example.rickMortyApp.network.Character
import com.example.rickMortyApp.network.CharacterResponse
import com.example.rickMortyApp.repository.Repository
import com.example.rickMortyApp.ulti.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    suspend fun fetchCharacterS(page: String) = repo.getCharacter(page)


}

//class CharacterViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel() {
//    private val _characterLiveData = MutableLiveData<ScreenState<List<com.example.rickMortyApp.network.Character>?>> ()
//    val characterLiveData: LiveData<ScreenState<List<Character>?>>
//        get() = _characterLiveData
//
//    init {
//        fetchCharacter()
//    }
//
//    private fun fetchCharacter(){
//        viewModelScope.launch {
//            val client = repository.getCharacter("1")
//            _characterLiveData.postValue(ScreenState.Loading(null))
//            client.enqueue(object : Callback<CharacterResponse>{
//                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {1
//                    throw(Error("Character: ${t.message.toString()}"))
//                    _characterLiveData.postValue(ScreenState.Error(t.message.toString(),null))
//                }
//                override fun onResponse(
//                    call: Call<CharacterResponse>,
//                    response: Response<CharacterResponse>
//                ) {
//                    if (response.isSuccessful){
//                        _characterLiveData.postValue(ScreenState.Success(response.body()?.results))
//                    } else {
//                        _characterLiveData.postValue(ScreenState.Error(response.code().toString()))
//                    }
//                }
//            })
//        }
//    }