package com.oguzhanaslann.twopanelayoutexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import coil.load
import com.oguzhanaslann.twopanelayoutexample.databinding.FragmentTreeDetailBinding

class TreeDetailFragment : Fragment(R.layout.fragment_tree_detail) {
    val treeNameArg : String
        get() =  arguments?.getString("name") ?: ""

    private var binder : FragmentTreeDetailBinding? = null
    val binding  get() = binder!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder = FragmentTreeDetailBinding.bind(view)
        val tree = trees.find { it.name == treeNameArg }
        tree?.let {
            binding.treeImage.load(it.image)
            binding.treeNameTextView.text = it.name
            binding.treeDescriptionTextView.text = it.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder = null
    }
}
