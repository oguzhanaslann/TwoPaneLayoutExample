package com.oguzhanaslann.twopanelayoutexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import coil.load
import com.oguzhanaslann.twopanelayoutexample.databinding.FragmentTreeListBinding
import com.oguzhanaslann.twopanelayoutexample.databinding.ItemTreeBinding

class TreeListFragment : Fragment(R.layout.fragment_tree_list) {

    private val treeViewModel: TreeViewModel by activityViewModels()

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
                    treeViewModel.setTree(it)
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



data class Tree(val name: String, val description: String, val image: String)

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


class SportsListOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    // Set the default 'enabled' state to true only if it is slidable (i.e., the panes
    // are overlapping) and open (i.e., the detail pane is visible).
    slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        // Return to the list pane when the system back button is pressed.
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) = Unit

    override fun onPanelOpened(panel: View) {
        // Intercept the system back button when the detail pane becomes visible.
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        // Disable intercepting the system back button when the user returns to the
        // list pane.
        isEnabled = false
    }
}
