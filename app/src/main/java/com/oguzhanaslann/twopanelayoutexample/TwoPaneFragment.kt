package com.oguzhanaslann.twopanelayoutexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.NavHostFragment
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

        binding.apply {
            treeRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TreeAdapter {
                    val detailNavController = detailPaneNavHostFragment.navController
                    detailNavController.navigate(
                        R.id.treeDetailFragment,
                        bundleOf("name" to it.name),
                        NavOptions.Builder()
                            .setPopUpTo(detailNavController.graph.startDestinationId, true)
                            .build()
                    )

                    slidingPaneLayout.open()
                }.also {
                    it.submitList(trees)
                }
            }
        }
    }

    override fun onCreateDetailPaneNavHostFragment(): NavHostFragment {
        return NavHostFragment.create(R.navigation.two_pane_navigation)
    }

}
