package com.okedoc.productdashboard.presentations.register

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.okedoc.productdashboard.databinding.FragmentRegisterSheetBinding
import com.okedoc.productdashboard.utils.observeUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragmentSheet : BottomSheetDialogFragment() {

    private var binding: FragmentRegisterSheetBinding? = null
    private val registerViewModel: RegisterViewModel by viewModels()

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
        binding = FragmentRegisterSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding?.buttonRegister?.setOnClickListener {
            registerViewModel.register(
                email = binding?.registerEmailInput?.text.toString(),
                password = binding?.registerPasswordInput?.text.toString()
            )
        }

        registerViewModel.register.observe(this) {
            activity?.observeUiState(it, progressBar) { message ->
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                dismiss()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = RegisterFragmentSheet()
    }
}