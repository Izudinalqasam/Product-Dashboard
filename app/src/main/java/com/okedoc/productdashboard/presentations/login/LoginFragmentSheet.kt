package com.okedoc.productdashboard.presentations.login

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.okedoc.productdashboard.data.local.PreferencesKeys
import com.okedoc.productdashboard.databinding.FragmentLoginSheetBinding
import com.okedoc.productdashboard.utils.observeUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragmentSheet : BottomSheetDialogFragment() {

    private var binding: FragmentLoginSheetBinding? = null

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var productDataStore : DataStore<Preferences>

    private val progressBar by lazy {
        ProgressBar(
            activity,
            null,
            attr.progressBarStyleLarge
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding?.buttonLogin?.setOnClickListener {
            loginViewModel.login(
                email = binding?.loginEmailInput?.text.toString(),
                password = binding?.loginPasswordInput?.text.toString()
            )
        }

        loginViewModel.login.observe(this) {
            activity?.observeUiState(it, progressBar) { message ->
                updateStatusLogin()
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                dismiss()
            }
        }
    }

    private fun updateStatusLogin() {
        activity?.lifecycleScope?.launch {
            productDataStore.edit { preferences ->
                preferences[PreferencesKeys.IS_LOGIN] = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragmentSheet()
    }
}