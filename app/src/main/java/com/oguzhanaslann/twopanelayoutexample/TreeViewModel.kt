package com.oguzhanaslann.twopanelayoutexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TreeViewModel:  ViewModel() {
    private val _tree = MutableLiveData<Tree>()
    val tree : LiveData<Tree> = _tree

    fun setTree(value : Tree) {
        _tree.value = value
    }
}
