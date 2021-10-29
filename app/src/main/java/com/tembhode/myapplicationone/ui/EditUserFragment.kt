package com.tembhode.myapplicationone.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.tembhode.myapplicationone.R
import com.tembhode.myapplicationone.data.UserDataRepository
import com.tembhode.myapplicationone.databinding.EditUserFragmentBinding
import com.tembhode.myapplicationone.databinding.UserListFragmentBinding
import com.tembhode.myapplicationone.models.User
import com.tembhode.myapplicationone.viewmodels.EditUserViewModel
import com.tembhode.myapplicationone.viewmodels.MainViewModel
import com.tembhode.myapplicationone.viewmodels.MyVMFactory
import com.tembhode.myapplicationone.viewmodels.UserListViewModel

class EditUserFragment : Fragment() {

    companion object {
        fun newInstance() = EditUserFragment()
    }

    private lateinit var user: User
    private lateinit var mContext: Context
    private lateinit var viewModel: EditUserViewModel
    private var _binding: EditUserFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var userDataRepository: UserDataRepository

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditUserFragmentBinding.inflate(inflater, container, false)
        userDataRepository = UserDataRepository(mContext)
        return binding.root
//        return inflater.inflate(R.layout.edit_user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = arguments?.get("user") as User
        viewModel = MyVMFactory(userDataRepository).create(EditUserViewModel::class.java)
        navController = findNavController()

        val booksList = resources.getStringArray(R.array.books_string_array)
        val adapter =
            ArrayAdapter(mContext, android.R.layout.simple_spinner_item, booksList)

        binding.etUserNameEdit.setText(user.name)
        binding.etMobileNumberEdit.setText(user.mobile)
        binding.spinnerBooksEdit.adapter = adapter
        val pos = booksList.indexOf(user.book)
        binding.spinnerBooksEdit.setSelection(pos)

        binding.buttonUpdateUser.setOnClickListener {
            val userName = binding.etUserNameEdit.text?.trim().toString()
            val mobile = binding.etMobileNumberEdit.text?.trim().toString()
            val book = booksList[binding.spinnerBooksEdit.selectedItemPosition]

            //Log.e("TAGTAG", "onAddClick: $userName, $mobile, $book")

            val user = User().also {
                it.id = user.id
                it.name = userName
                it.mobile = mobile
                it.book = book
            }
            viewModel.updateData(user)
        }

        viewModel.isUpdatDone.observe(viewLifecycleOwner, Observer {
            Log.e("TAGTAG", "onViewCreated: Update=$it")
            // navigate to UsersList page / Show dialog
            Snackbar.make(view, "User updated successfully..", Snackbar.LENGTH_SHORT).show()
            navController.popBackStack()
        })
    }


}