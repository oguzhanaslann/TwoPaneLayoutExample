package com.oguzhanaslann.twopanelayoutexample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.oguzhanaslann.twopanelayoutexample.databinding.FragmentTreeDetailBinding

class TreeDetailFragment : Fragment(R.layout.fragment_tree_detail) {

    private var binder : FragmentTreeDetailBinding? = null
    val binding get() = binder!!

    private val treeViewModel: TreeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder = FragmentTreeDetailBinding.bind(view)

        treeViewModel.tree.observe(viewLifecycleOwner) {
            binding.apply {
                treeNameTextView.text = it.name
                treeDescriptionTextView.text = it.description
                treeImage.load(it.image)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder = null
    }
}
