package com.example.gamesproj.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.gamesproj.R
import com.example.gamesproj.databinding.FragmentGamesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {
    //region Variables
    private val _viewModel: GameViewModel by viewModel()
    private lateinit var _binding: FragmentGamesBinding
    private lateinit var gameAdapter: GameAdapter

    //endregion

    //region Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment.
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_games, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize xml view model.
        _binding.viewModel = _viewModel

        // Setup layout.
        _binding.root.setInsetsPadding(topBottomView = _binding.layoutHome)


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
        setupView()
    }

    private fun observeNavigateBack() {
        _viewModel.navigateBack.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let {
                // Only proceed if the event has never been handled.
                activity?.finish()
            }
        })
    }

    private fun setupView() {
        gameAdapter= GameAdapter()
            _viewModel.list?.observe(viewLifecycleOwner) {
                gameAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                _binding.recycleView.apply {
                    adapter = gameAdapter
                }
            }
    }
//endregion
}