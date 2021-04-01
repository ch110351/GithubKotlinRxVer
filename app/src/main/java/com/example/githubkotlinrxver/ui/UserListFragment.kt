package com.example.githubkotlinrxver.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubkotlinrxver.R
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.databinding.UsersListFragmentBinding
import com.example.githubkotlinrxver.ui.adapter.UserListAdapter
import com.example.githubkotlinrxver.viewmodel.UserListViewModel
import com.example.githubkotlinrxver.widget.ItemClick
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserListFragment : Fragment() {

    private lateinit var viewBinding: UsersListFragmentBinding
    private val userListViewModel: UserListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.users_list_fragment, container, false
        )
        viewBinding.lifecycleOwner = this
        initView()
        return viewBinding.root
    }

    private fun initView() {
        val adapter = UserListAdapter(object : ItemClick<UserListItem> {
            override fun onClicked(view: View, userListItem: UserListItem) {
                Log.d("Wesley","userListItem id" + userListItem.id)
                //跳轉至user info頁面
            }
        })
        viewBinding.usersListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    viewBinding.usersListView.context,
                    (viewBinding.usersListView.layoutManager as LinearLayoutManager).orientation
                )
            )
            this.adapter = adapter
        }

        userListViewModel.pagedList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }


}