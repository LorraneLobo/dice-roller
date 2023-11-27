package com.example.diceroll.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.diceroll.databinding.ActivityMainBinding
import com.example.diceroll.presentation.viewmodel.MainViewModel
import com.nambimobile.widgets.efab.FabOption
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configDicesListView()
        setLifeCycleScopes()
        setupMotionLayout()
    }

    private fun configDicesListView() {
        viewModel.diceList.forEach { dice ->
            val fab = FabOption(context = this).apply {
                label.labelText = "Rolar D${dice.faces}"
                fabOptionIcon = AppCompatResources.getDrawable(this@MainActivity, dice.image)
                setOnClickListener {
                    viewModel.selectDice(dice)
                }
            }
            binding.diceButtons.addView(fab)
        }
    }

    private fun setLifeCycleScopes() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.diceResult
                    .collect { diceResult ->
                        binding.tvDiceNumber.visibility = View.VISIBLE
                        binding.tvDiceNumber.text = diceResult?.toString().orEmpty()
                    }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.diceImage.collect {
                    binding.tvDiceNumber.visibility = View.GONE
                    binding.imgDice.setImageResource(it)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedDice.collect {
                    binding.imgDice.setImageResource(it.image)
                }
            }
        }
    }

    private fun setupMotionLayout() {
        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                binding.tvDiceNumber.visibility = View.GONE
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                viewModel.rollDice()
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })
    }
}
