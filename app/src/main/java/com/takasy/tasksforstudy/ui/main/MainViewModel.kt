package com.takasy.tasksforstudy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class MainViewModel : ViewModel() {

    fun fetchContents(): LiveData<List<ContentsAdapter.Content>> = liveData {
        val contents = (0..8).map { i ->
            ContentsAdapter.Content(
                name = "image $i",
                imageColor = if (i % 2 == 0) {
                    ContentsAdapter.Content.Color.ORANGE
                } else {
                    ContentsAdapter.Content.Color.BLUE
                }
            )
        }
        emit(contents)
    }
}