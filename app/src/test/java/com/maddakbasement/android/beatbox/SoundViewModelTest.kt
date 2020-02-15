package com.maddakbasement.android.beatbox

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class SoundViewModelTest {

    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        sound = Sound("assetPath")
        subject = SoundViewModel()
        subject.sound = sound
    }

    @Test
    fun exposesSoundsNameAsTitle(){
        assertThat(subject.title, `is`(sound.name))
    }

    @Test
    fun callsBeatBoxOnButtonClicked(){
        subject.onButtonClicked()
    }
}