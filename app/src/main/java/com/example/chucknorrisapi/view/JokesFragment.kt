package com.example.chucknorrisapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chucknorrisapi.R
import com.example.chucknorrisapi.model.ItemJoke
import kotlinx.android.synthetic.main.layout_fragment_input_character.*
import kotlinx.android.synthetic.main.layout_fragment_joke.*

class JokesFragment : Fragment() {

    companion object {
        const val SHOW_JOKE_ARG = "show_joke"
        const val SHOW_JOKE_CHARACTER = "show_char"
        const val SHOW_ENDLESS_CHAR = "show_rv"

        fun getInstance(
            singleJoke: Int = 0,
            showChar: Int = 0,
            showRV: Int = 0
        ): JokesFragment {
            val fragment = JokesFragment()
            val bundle = Bundle()
            bundle.putInt(SHOW_JOKE_ARG, singleJoke)
            bundle.putInt(SHOW_JOKE_CHARACTER, showChar)
            bundle.putInt(SHOW_ENDLESS_CHAR, showRV)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return when (arguments != null) {
            arguments!!.getInt(SHOW_JOKE_ARG) == 1 -> {
                inflater.inflate(
                    R.layout.layout_fragment_joke,
                    container,
                    false
                )
            }
            arguments!!.getInt(SHOW_JOKE_CHARACTER) == 1 -> {
                inflater.inflate(
                    R.layout.layout_fragment_input_character,
                    container,
                    false
                )
            }
            else -> {
                inflater.inflate(
                    R.layout.layout_fragment_endless,
                    container,
                    false
                )
            }
        }
    }

    fun inflateJokeData(joke: ItemJoke) {
        tv_fragment_joke.text = joke.joke
    }

    fun inflateCharacterData() {
        btn_character_submit.setOnClickListener {
            (activity as MainActivity).getCharData(
                et_firstName.text.toString(),
                et_lastName.text.toString()
            )
        }
    }

//    fun inflateRVData(dataSet: List<ItemJoke>) {
//        val adapter:
//    }

}