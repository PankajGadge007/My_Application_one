package com.tembhode.myapplicationone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tembhode.myapplicationone.R
import com.tembhode.myapplicationone.databinding.MainFragmentBinding
import com.tembhode.myapplicationone.databinding.UserListFragmentBinding
import com.tembhode.myapplicationone.viewmodels.UserListViewModel

class UserListFragment : Fragment() {

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)


    }

}