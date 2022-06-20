package com.oguzhanaslann.twopanelayoutexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanaslann.twopanelayoutexample.databinding.FragmentTreeListBinding

class TwoPaneFragment : AbstractListDetailFragment() {
    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tree_list, container, false)
    }

    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
        val binding = FragmentTreeListBinding.bind(view)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(binding.slidingPane)
        )

        binding.apply {
            treeRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TreeAdapter {
                    val detailNavController = detailPaneNavHostFragment.navController
                    detailNavController.navigate(
                        R.id.treeDetailFragment,
                        null,
                        NavOptions.Builder()
                            .setPopUpTo(detailNavController.graph.startDestinationId, true)
                            .build()
                    )

                    slidingPaneLayout.open()
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
