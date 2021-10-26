package dev.sertan.android.videogames.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.videogames.R
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.databinding.FragmentHomeBinding
import dev.sertan.android.videogames.ui.adapter.GameListAdapter
import dev.sertan.android.videogames.util.GameClickListener

@AndroidEntryPoint
internal class HomeFragment : Fragment(), GameClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        return binding.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.viewPagerGames.adapter = GameViewPagerAdapter(this)
        binding.recyclerViewGames.adapter = GameListAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerGames) { _, _ -> }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickGame(game: Game) {
        val action = HomeFragmentDirections.actionHomeToGameDetails(game.id ?: return)
        findNavController().navigate(action)
    }

}
