package com.example.chucknorrisapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.chucknorrisapi.R
import com.example.chucknorrisapi.model.ChuckResponse
import com.example.chucknorrisapi.model.ItemJoke
import com.example.chucknorrisapi.viewmodel.ChuckViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val jokeViewModel: ChuckViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_single_joke.setOnClickListener { initSingleJokeFragment() }

        btn_character_joke.setOnClickListener { initCharacterJokeFragment() }
    }

    fun getCharData(firstName: String, lastName: String) {
        val fragment = JokesFragment.getInstance(1)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
            fragment,
            "SingleJokeFragment")
            .addToBackStack("SingleJokeFragment")
            .commit()

        jokeViewModel.getMeCharacters(firstName, lastName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : Observer<ChuckResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onNext(t: ChuckResponse?) {
                        t?.value?.let { fragment.inflateJokeData(it) }
                    }

                    override fun onError(e: Throwable?) {

                    }
                }
            )
    }

    fun initSingleJokeFragment() {
        val fragment = JokesFragment.getInstance(showChar = 1)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                fragment,
                "SingleJokeFragment"
            )
            .addToBackStack(null)
            .commit()
        jokeViewModel.getMeSingleJoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : Observer<ChuckResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onNext(t: ChuckResponse?) {
                        t?.let {
                            fragment.inflateJokeData(it.value)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("MainActivity", "error message")
                        e?.printStackTrace()
                    }
                }
            )
    }

    fun initCharacterJokeFragment() {
        val fragment = JokesFragment.getInstance(showChar = 1)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                fragment,
                "CharacterJokeFragment"
            )
            .addToBackStack(null)
            .commit()

        supportFragmentManager.executePendingTransactions()
        fragment.inflateCharacterData()
    }
}
