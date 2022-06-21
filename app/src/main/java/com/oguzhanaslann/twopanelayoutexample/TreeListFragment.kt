package com.oguzhanaslann.twopanelayoutexample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanaslann.twopanelayoutexample.databinding.FragmentTreeListBinding

class TreeListFragment : Fragment(R.layout.fragment_tree_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTreeListBinding.bind(view)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(binding.slidingPane)
        )

        binding.apply {
            treeRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TreeAdapter {
                    slidingPane.openPane()
                }.also {
                    it.submitList(
                        listOf(
                            Tree(
                                name = "Oak",
                                description = "A tree that is native to the American West",
                                image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                            ),
                            Tree(
                                name = "Pine",
                                description = "A tree that is native to the American West",
                                image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
                            ),
                            Tree(
                                name = "Cherry",
                                description = "A tree that is native to the American West",
                                image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"

                            ),
                        )
                    )
                }
            }
        }
    }
}
