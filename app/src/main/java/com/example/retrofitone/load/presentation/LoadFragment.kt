package com.example.retrofitone.load.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofitone.core.di.ManageViewModels
import com.example.retrofitone.databinding.FragmentLoadBinding

class LoadFragment : Fragment(){

    private var _binding: FragmentLoadBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoadViewModel
    private lateinit var showUi: (LoadUiState) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manageViewModels = activity as ManageViewModels
        viewModel = manageViewModels.viewModel(LoadViewModel::class.java)


        showUi = { loadUiState ->
            loadUiState.update(
                binding.progressBar,
                binding.errorTextView,
                binding.retryButton
            )
            loadUiState.navigate {
                manageViewModels.clear(LoadViewModel::class.java)
                (requireActivity() as LoadNavigation).navigateFromLoad()
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.retry()
        }

        viewModel.init(firstRun = savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startGetUpdates(showUi)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopGetUpdates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

interface LoadNavigation {

    fun navigateFromLoad()
}