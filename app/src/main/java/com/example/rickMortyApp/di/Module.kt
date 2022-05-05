package com.example.rickMortyApp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


class  TempData(){
    fun show() = println("Hello World")


}

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    fun tempData(): TempData = TempData()

}