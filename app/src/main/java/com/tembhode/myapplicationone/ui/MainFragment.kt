package com.tembhode.myapplicationone.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tembhode.myapplicationone.R
import com.tembhode.myapplicationone.databinding.MainFragmentBinding
import com.tembhode.myapplicationone.viewmodels.MainViewModel

class MainFragment : Fragment() {

//    companion object {
//        fun newInstance() = MainFragment()
//    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val booksList = resources.getStringArray(R.array.books_string_array)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, booksList)

        binding.buttonAddUser.setOnClickListener {
            val userName = binding.etUserName.text?.trim()
            val mobile = binding.etMobileNumber.text?.trim()
            val book = booksList[binding.spinnerBooks.selectedItemPosition]

            Log.e("TAGTAG", "onAddClick: $userName, $mobile, $book")

        }
        binding.buttonViewAll.setOnClickListener {

        }

        binding.spinnerBooks.adapter = adapter

    }

}