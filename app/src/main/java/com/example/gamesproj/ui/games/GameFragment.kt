package com.example.gamesproj.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import com.example.gamesproj.R
import com.example.gamesproj.databinding.FragmentGamesBinding
import com.example.gamesproj.utils.extension.getNavController
import com.example.gamesproj.utils.extension.setInsetsPadding
import com.example.gamesproj.utils.extension.showIf
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
        setupView()
        loadGamesState()
    }

    private fun setupView() {
        gameAdapter= GameAdapter()
        _viewModel.gamePagingData?.observe(viewLifecycleOwner) {
            gameAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            _binding.recycleView.apply {
                adapter = gameAdapter.withLoadStateHeaderAndFooter(
                    header = GameStateFooterAdapter().apply {
                        onRetryClickListener = { gameAdapter.retry() }
                    },
                    footer = GameStateFooterAdapter().apply {
                        onRetryClickListener = { gameAdapter.retry() }
                    }
                )
            }
        }
    }
    private fun loadGamesState(){
        gameAdapter.addLoadStateListener { loadState ->
            _binding.apply {
                recycleView.showIf(loadState.source.refresh is LoadState.NotLoading)
                progressLoading.showIf(loadState.source.refresh is LoadState.Loading)
                _binding.layoutErrorBinding.layoutError.showIf(loadState.source.refresh is LoadState.Error)
                _binding.layoutEmptyListBinding.layoutEmpty.showIf(loadState.source.refresh is LoadState.NotLoading
                        && loadState.append.endOfPaginationReached && gameAdapter.itemCount < 1)

                if(loadState.source.refresh is LoadState.Error)
                    _binding.layoutErrorBinding.tvError.setText(R.string.list_not_found)

                if(loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached && gameAdapter.itemCount < 1)
                    _binding.layoutEmptyListBinding.tvEmpty.setText(R.string.empty_list)
            }
        }
    }

//endregion
}