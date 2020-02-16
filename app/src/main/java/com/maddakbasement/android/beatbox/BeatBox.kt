package com.maddakbasement.android.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        val soundNames = assets.list(SOUNDS_FOLDER) ?: emptyArray()
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException){
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
        return sounds
    }

    private fun load(sound: Sound){
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd,1)
        sound.soundId = soundId
    }

    fun play(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it,1.0f, 1.0f, 1,0,1.0f)
        }
    }

    fun release(){
        soundPool.release()
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