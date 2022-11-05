package com.example.githubkotlinrxver.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubkotlinrxver.R
import com.example.githubkotlinrxver.data.UserListItem
import com.example.githubkotlinrxver.databinding.UsersListFragmentBinding
import com.example.githubkotlinrxver.ui.adapter.UserPageListAdapter
import com.example.githubkotlinrxver.viewmodel.UserListViewModel
import com.example.githubkotlinrxver.widget.OnItemClickListener
import com.example.githubkotlinrxver.widget.getNetworkLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Users list ui
 */
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
        val userPageListAdapter = UserPageListAdapter(object : OnItemClickListener<UserListItem> {
            override fun onItemClick(t: UserListItem) {
                //跳轉至user info頁面
                val usersList2UserInfo =
                    UserListFragmentDirections.actionUsersListFragmentToUserInfoFragment(
                        t.login
                    )
                view?.let { it ->
                    Navigation.findNavController(it)
                        .navigate(usersList2UserInfo)
                }

            }
        })

        viewBinding.recycleViewUsers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    viewBinding.recycleViewUsers.context,
                    (viewBinding.recycleViewUsers.layoutManager as LinearLayoutManager).orientation
                )
            )
            this.adapter = userPageListAdapter
        }

        userListViewModel.pagedList.observe(viewLifecycleOwner, Observer { pageList ->
            userPageListAdapter.submitList(pageList)
        })

        getNetworkLiveData().observe(viewLifecycleOwner, Observer { available ->
            if (!available) {
                Toast.makeText(activity, R.string.network_not_available, Toast.LENGTH_SHORT).show()
            }
        })
    }


}