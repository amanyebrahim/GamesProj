package com.example.gamesproj.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gamesproj.R
import com.example.gamesproj.databinding.FragmentHomeBinding
import com.example.gamesproject.utils.extension.setInsetsPadding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment() {
    //region Variables
    private val _viewModel: HomeViewModel by viewModel()
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var gameAdapter: GameAdapter

    //endregion

    //region Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment.
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
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
    @SuppressLint("TimberArgCount")
    private fun setupView() {
        gameAdapter= GameAdapter()
        Timber.v("data","data")
            _viewModel.list.observe(viewLifecycleOwner) {
                Timber.v("data","data")
                gameAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                Timber.v("data","$it")
                _binding.recycleView.apply {
                    adapter = gameAdapter
                }
            }
    }
//endregion
}