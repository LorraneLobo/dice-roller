package com.example.diceroll.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroll.databinding.AdapterDiceResultBinding
import com.example.diceroll.domain.DiceResult

class ResultDiceAdapter: ListAdapter<DiceResult, ResultDiceAdapter.MyViewHolder>(this) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemBinding = AdapterDiceResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun submitList(list: List<DiceResult>?) {
        super.submitList(list?.takeLast(3))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class MyViewHolder(private val binding: AdapterDiceResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diceResult: DiceResult) {
            binding.ivDice.setImageResource(diceResult.dice.image)
            binding.tvResult.text = diceResult.result.toString()
        }
    }

    companion object : DiffUtil.ItemCallback<DiceResult>() {
        override fun areItemsTheSame(oldItem: DiceResult, newItem: DiceResult): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DiceResult, newItem: DiceResult): Boolean {
            return oldItem == newItem
        }
    }
}