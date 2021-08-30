package com.example.gamesproj.ui.gameDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.gamesproj.R
import com.example.gamesproj.databinding.FragmentGameDetailsBinding
import com.example.gamesproj.utils.extension.getNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameDetailsFragment : Fragment() {
    //region Variables
    private val _viewModel: GameDetailsViewModel by viewModel()
    private lateinit var _binding: FragmentGameDetailsBinding
    //endregion

    //region Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment.
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_details, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize xml view model.
        _binding.viewModel = _viewModel


        arguments?.let {
            val args = GameDetailsFragmentArgs.fromBundle(it)
            _viewModel.gotArgs(args.gameId)
        }
        // Observe view model data.
        observeViewModel()
    }
    //endregion

    //region Private methods
    /**
     * Call each function that will observe variable from view model.
     */
    private fun observeViewModel() {
        observeNavigateBack()
        observeErrorViewVisibility()
    }

    private fun observeNavigateBack() {
        _viewModel.navigateBack.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let {
                // Only proceed if the event has never been handled.
                getNavController()?.navigateUp()
            }
        })
    }
    private fun observeErrorViewVisibility() {
        _viewModel.errorViewVisibility.observe(viewLifecycleOwner, {
            it?.let {
                when (val errorMessageId = _viewModel.errorMessageId.value) {
                    null -> _binding.layoutErrorBinding.tvError.text = _viewModel.errorMessage.value
                    else -> _binding.layoutErrorBinding.tvError.setText(errorMessageId)
                }

                _binding.layoutErrorBinding.layoutError.visibility = it
            }
        })
    }
//endregion
}