package com.maddakbasement.android.beatbox

import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maddakbasement.android.beatbox.databinding.ActivityMainBinding
import com.maddakbasement.android.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = ActivityViewModel(beatBox, binding.seekBar)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }


//        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                beatBox.playbackRate=1.0f + (progress-50)/100
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundAdapter.SoundHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater, R.layout.list_item_sound, parent, false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

        override fun getItemCount() = sounds.size

        private inner class SoundHolder(private val binding: ListItemSoundBinding) :
            RecyclerView.ViewHolder(binding.root) {
            init {
                binding.viewModel = SoundViewModel(beatBox)
            }

            fun bind(sound: Sound) {
                binding.apply {
                    viewModel?.sound = sound
                    executePendingBindings()
                }
            }
        }
    }
}
