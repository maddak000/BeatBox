package com.maddakbasement.android.beatbox

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {

    fun loadSounds(): List<String> {
        val soundNames = assets.list(SOUNDS_FOLDER)
        Log.d(TAG, "Found ${soundNames?.size ?: 0} sounds")
        return soundNames?.asList() ?: emptyList()
    }

//    fun loadSounds(): List<String> {
//        return try {
//            val soundNames = assets.list(SOUNDS_FOLDER)!!
//            Log.d(TAG, "Found ${soundNames.size} sounds")
//            soundNames.asList()
//        } catch (e: Exception) {
//            Log.e(TAG, "Could not list assets", e)
//            emptyList()
//        }
//    }
}