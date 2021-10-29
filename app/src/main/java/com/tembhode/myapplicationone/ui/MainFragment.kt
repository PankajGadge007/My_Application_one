package com.tembhode.myapplicationone.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.google.android.material.snackbar.Snackbar
import com.tembhode.myapplicationone.R
import com.tembhode.myapplicationone.adapters.UsersListAdapter
import com.tembhode.myapplicationone.data.UserDataRepository
import com.tembhode.myapplicationone.databinding.MainFragmentBinding
import com.tembhode.myapplicationone.models.User
import com.tembhode.myapplicationone.viewmodels.MainViewModel
import com.tembhode.myapplicationone.viewmodels.MyVMFactory

class MainFragment : Fragment() {

//    companion object {
//        fun newInstance() = MainFragment()
//    }

    private lateinit var booksList: Array<String>
    private lateinit var navController: NavController
    private lateinit var userDataRepository: UserDataRepository
    private lateinit var mContext: Context
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        userDataRepository = UserDataRepository(mContext)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyVMFactory(userDataRepository).create(MainViewModel::class.java)
        navController = findNavController()

        booksList = resources.getStringArray(R.array.books_string_array)
        val adapter =
            ArrayAdapter(mContext, android.R.layout.simple_spinner_item, booksList)

        binding.buttonAddUser.setOnClickListener {
            val userName = binding.etUserName.text?.trim().toString()
            val mobile = binding.etMobileNumber.text?.trim().toString()
            val book = booksList[binding.spinnerBooks.selectedItemPosition]

            Log.e("TAGTAG", "onAddClick: $userName, $mobile, $book")

            if (isValidData(userName, mobile, book)) {
                val user = User().also {
                    it.name = userName
                    it.mobile = mobile
                    it.book = book
                }
                viewModel.insertData(user)
            } else {
                Toast.makeText(mContext, EditUserFragment.msg, Toast.LENGTH_SHORT).show()
            }


        }
        binding.buttonViewAll.setOnClickListener {
            navController.navigate(R.id.userListFragment)
        }

        binding.spinnerBooks.adapter = adapter

        viewModel.getUsers()

        //Observer
        viewModel.allUserList.observe(viewLifecycleOwner, Observer { list ->
            binding.buttonViewAll.isEnabled = list.isNotEmpty()
        })
        viewModel.isInserted.observe(viewLifecycleOwner, Observer {
//            Log.e("TAGTAG", "onViewCreated: isInserted=$it" )
            if (it > 0) {
                // navigate to UsersList page / Show dialog
                Snackbar.make(view, "User added successfully..", Snackbar.LENGTH_SHORT).show()
                binding.etUserName.setText("")
                binding.etMobileNumber.setText("")
                binding.spinnerBooks.setSelection(0)
                binding.buttonViewAll.isEnabled = true
            }
        })
    }

    private fun isValidData(userName: String, mobile: String, book: String?): Boolean {
        var vv = false
        if (userName.trim().isEmpty()) {
            EditUserFragment.msg = "Please enter proper name."
            return vv
        } else if (mobile.length!=10) {
            EditUserFragment.msg = "Please enter valid mobile number."
            return vv
        } else if (book.contentEquals(booksList[0])) {
            EditUserFragment.msg = "Please select book."
            return vv
        }
        return true
    }

}