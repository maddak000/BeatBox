package com.maddakbasement.android.beatbox

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        val soundNames = assets.list(SOUNDS_FOLDER) ?: emptyArray()
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            sounds.add(sound)
        }
        return sounds
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