package dev.sertan.android.videogames.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.videogames.R
import dev.sertan.android.videogames.databinding.FragmentGameDetailsBinding

@AndroidEntryPoint
internal class GameDetailsFragment : Fragment() {
    private var _binding: FragmentGameDetailsBinding? = null
    private val binding: FragmentGameDetailsBinding get() = _binding!!

    private val viewModel: GameDetailsViewModel by viewModels()
    private val args: GameDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_details,
            container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel.apply { loadGame(args.gameId) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}