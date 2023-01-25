package com.example.testapp.model

import android.util.Log
import com.example.testapp.data.CardDetails
import com.example.testapp.data.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardRepository {

    private var cardList = mutableListOf<String>()

    fun getCardInfo(
        bin: String,
        callback: (CardDetails?) -> Unit,
        processCallback: (String) -> Unit,
        listCallback: (List<String>) -> Unit
    ) {
        cardList.add(bin)
        listCallback(cardList.toList())
        Networking.binlistApi.getCard(bin).enqueue(object :
            Callback<CardDetails> {
            override fun onResponse(call: Call<CardDetails>, response: Response<CardDetails>) {

                callback(response.body())
                processCallback("Card Info BIN-$bin")

                Log.v("from response", response.body().toString())
            }

            override fun onFailure(call: Call<CardDetails>, t: Throwable) {
                processCallback(t.message.toString())
                Log.v("from failure", t.message.toString())
            }

        })
    }
}