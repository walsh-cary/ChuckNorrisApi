package com.example.chucknorrisapi.model

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisInterface {

    //http://api.icndb.com/jokes/random/
    //http://api.icndb.com/jokes/random/20
    //http://api.icndb.com/jokes/random?firstName=John&amp;lastName=Doe

    @GET("jokes/random/")
    fun getSingleJoke() : Observable<ChuckResponse>

    @GET("jokes/random/20")
    fun getEndlessJoke() : Observable<ChuckResponseList>

    @GET("jokes/random")
    fun getCharacterJoke(
        @Query("firstName") userName: String,
        @Query("lastName") userLastName: String) : Observable<ChuckResponse>

    companion object {
        const val BASE_URL =
            "http://api.icndb.com/"
        fun initRetrofit() : ChuckNorrisInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ChuckNorrisInterface::class.java)
        }
    }
}