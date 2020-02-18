package com.maddakbasement.android.beatbox

import android.util.Log
import android.widget.SeekBar
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ActivityViewModel(private val beatBox: BeatBox, private val seekBar:SeekBar) : BaseObservable() {


    fun onSeekBarChanged() {
        beatBox.playbackRate = 2.0f
        playbackRate = (1.0f + (seekBar.progress-50.0)/100.0).toString()
        Log.d("WHATEVER", "on SeekBarChanged")
    }

    @Bindable
    var playbackRate = beatBox.playbackRate.toString()
        set(playbackRate) {
            field = playbackRate
            notifyPropertyChanged(BR.playbackRate)
        }
}