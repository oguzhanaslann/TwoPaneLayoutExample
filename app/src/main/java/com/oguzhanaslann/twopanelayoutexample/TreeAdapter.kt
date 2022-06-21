package com.oguzhanaslann.twopanelayoutexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oguzhanaslann.twopanelayoutexample.databinding.ItemTreeBinding

class TreeAdapter(
    private inline var onClick: (Tree) -> Unit = {}
) : ListAdapter<Tree, TreeAdapter.Holder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemTreeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = getItem(position)
        holder.onBind(currentItem)
    }

    inner class Holder(val binding: ItemTreeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(currentItem: Tree) = binding.run {
            cardView.setOnClickListener {
                onClick(currentItem)
            }
            treeNameTextView.text = currentItem.name
            treeImage.load(currentItem.image)
        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<Tree>() {
        override fun areItemsTheSame(oldItem: Tree, newItem: Tree): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Tree, newItem: Tree): Boolean = oldItem == newItem
    }
}
