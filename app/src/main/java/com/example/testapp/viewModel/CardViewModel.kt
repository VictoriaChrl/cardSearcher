package com.example.testapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.data.CardDetails
import com.example.testapp.model.CardRepository

class CardViewModel: ViewModel() {

    private val repository = CardRepository()

    private val cardInfoLiveData = MutableLiveData<CardDetails?>()
    private val messageLiveData = MutableLiveData<String>()
    private val listLiveData = MutableLiveData<List<String>>()

    val message: LiveData<String>
        get() = messageLiveData

    val cardInfo: LiveData<CardDetails?>
        get() = cardInfoLiveData

    val list: LiveData<List<String>>
        get() = listLiveData

    fun search(bin: String){
        repository.getCardInfo(
            bin,
            {cardInfoLiveData.postValue(it)},
            {messageLiveData.postValue(it)},
            {listLiveData.postValue(it)}
        )
    }

}