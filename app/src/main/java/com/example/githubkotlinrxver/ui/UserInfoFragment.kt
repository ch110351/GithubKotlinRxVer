package com.example.githubkotlinrxver.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.githubkotlinrxver.R
import com.example.githubkotlinrxver.databinding.UserInfoFragmentBinding
import com.example.githubkotlinrxver.viewmodel.UserInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Users info ui
 */
class UserInfoFragment : Fragment() {

    private lateinit var viewBinding: UserInfoFragmentBinding
    private val userInfoViewModel: UserInfoViewModel by viewModel()
    private val args: UserInfoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userInfoViewModel.userLogin = args.userLogin
        userInfoViewModel.getUserInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.user_info_fragment, container, false
        )
        viewBinding.lifecycleOwner = this
        initView()
        return viewBinding.root
    }


    private fun initView() {
        userInfoViewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let { userInfo ->
                viewBinding.user = userInfo
            }
        })

        userInfoViewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(activity, R.string.network_not_available, Toast.LENGTH_SHORT).show()
            }
        })
    }
}