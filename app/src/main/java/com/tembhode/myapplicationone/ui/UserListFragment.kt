package com.tembhode.myapplicationone.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tembhode.myapplicationone.adapters.UsersListAdapter
import com.tembhode.myapplicationone.data.UserDataRepository
import com.tembhode.myapplicationone.databinding.UserListFragmentBinding
import com.tembhode.myapplicationone.models.User
import com.tembhode.myapplicationone.viewmodels.MyVMFactory
import com.tembhode.myapplicationone.viewmodels.UserListViewModel

class UserListFragment : Fragment(), UsersListAdapter.UserListListener {

    private lateinit var adapter: UsersListAdapter
    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserListViewModel
    private lateinit var userDataRepository: UserDataRepository
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        userDataRepository = UserDataRepository(mContext)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyVMFactory(userDataRepository).create(UserListViewModel::class.java)

        binding.rvUsersList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        viewModel.getUsersList()

        viewModel.usersList.observe(viewLifecycleOwner, Observer { list ->
            adapter = UsersListAdapter(list as ArrayList<User>, this)
            binding.rvUsersList.adapter = adapter
        })
    }

    override fun onCardClick(u: User) {
//        Toast.makeText(mContext,"Card ${u.name}",Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClick(u: User, position: Int) {
        viewModel.deleteData(u)
    }

    override fun onEditClick(u: User) {
        Toast.makeText(mContext, "Edit ${u.name}", Toast.LENGTH_SHORT).show()
    }

}