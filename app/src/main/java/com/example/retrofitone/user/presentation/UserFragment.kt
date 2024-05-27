package com.example.retrofitone.user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofitone.core.di.ManageViewModels
import com.example.retrofitone.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var userUiState: UserUiState

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manageViewModels = activity as ManageViewModels
        viewModel = manageViewModels.viewModel(UserViewModel::class.java)


        binding.loadNextButton.setOnClickListener {
            manageViewModels.clear(UserViewModel::class.java)
            (requireActivity() as UserNavigation).navigateFromUser()

        }

        val showUi: () -> Unit = {
            userUiState.update(
                genderView = binding.genderTextView,
                cityView = binding.cityTextView,
            )
        }

        userUiState = viewModel.init(savedInstanceState == null)
        showUi.invoke()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface UserNavigation {

    fun navigateFromUser()
}