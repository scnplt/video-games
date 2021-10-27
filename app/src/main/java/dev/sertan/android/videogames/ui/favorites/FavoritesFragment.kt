package dev.sertan.android.videogames.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.videogames.R
import dev.sertan.android.videogames.databinding.FragmentFavoritesBinding
import dev.sertan.android.videogames.ui.adapter.GameListAdapter
import dev.sertan.android.videogames.ui.adapter.viewholder.GameListViewHolder

@AndroidEntryPoint
internal class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorites,
            container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        setUpRecyclerViewAdapter()
        return binding.root
    }

    private fun setUpRecyclerViewAdapter() {
        binding.recyclerViewGames.adapter = GameListAdapter { parent ->
            GameListViewHolder.createInstance(parent) {
                val action = FavoritesFragmentDirections.actionFavoritesToGameDetails(it.id!!)
                findNavController().navigate(action)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
