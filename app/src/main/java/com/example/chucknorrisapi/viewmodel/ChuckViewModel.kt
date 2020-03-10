package com.example.chucknorrisapi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chucknorrisapi.model.ChuckNorrisInterface
import com.example.chucknorrisapi.model.ChuckResponse
import com.example.chucknorrisapi.model.ChuckResponseList
import com.example.chucknorrisapi.model.ItemJoke
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ChuckViewModel : ViewModel() {
    fun getMeSingleJoke(): Observable<ChuckResponse> {
        return ChuckNorrisInterface.initRetrofit()
            .getSingleJoke()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<ItemJoke>{
//                override fun onComplete() {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onSubscribe(d: Disposable?) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onNext(t: ItemJoke?) {
//                    //todo pass data to the view...
//                }
//
//                override fun onError(e: Throwable?) {
//                    TODO("Not yet implemented")
//                }
//            })
    }

    fun getMeCharacters(name: String, lastName: String): Observable<ChuckResponse> {
        return ChuckNorrisInterface.initRetrofit()
            .getCharacterJoke(name, lastName)
    }

    fun getMeEndlessJoke() : Observable<ChuckResponseList> {
        return ChuckNorrisInterface.initRetrofit()
            .getEndlessJoke()
    }
}