package com.example.chucknorrisapi.model

data class ItemJoke (var id: Int,
                    var joke: String,
                    var categories: List<String>)

data class ChuckResponse(
    var type: String,
    var value: ItemJoke
)

data class ChuckResponseList(
    var type: String,
    var value: List<ItemJoke>
)