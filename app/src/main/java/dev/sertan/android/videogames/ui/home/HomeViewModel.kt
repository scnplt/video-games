package dev.sertan.android.videogames.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sertan.android.videogames.data.model.Game
import dev.sertan.android.videogames.data.repository.GameRepository
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(private val repo: GameRepository) : ViewModel() {
    val searchText = MutableLiveData("")
    val searchActive: LiveData<Boolean> = Transformations.map(searchText) { it.length >= 3 }
    private val allGames: LiveData<List<Game>> = repo.games.asLiveData()

    private val searchResult: LiveData<List<Game>> = Transformations.switchMap(searchText) {
        liveData { emit(if (it.length >= 3) repo.searchGame(it) else null) }
    }

    val showError: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(searchActive) { value = it && searchResult.value.isNullOrEmpty() }
        addSource(searchResult) { value = it.isNullOrEmpty() && searchActive.value == true }
    }

    val recyclerViewGames: LiveData<List<Game>> = MediatorLiveData<List<Game>>().apply {
        addSource(searchActive) { value = getRecyclerViewGames() }
        addSource(allGames) { value = getRecyclerViewGames() }
        addSource(searchResult) { value = getRecyclerViewGames() }
    }

    val viewPagerGames: LiveData<List<Game>> = MediatorLiveData<List<Game>>().apply {
        addSource(searchActive) { value = getViewPagerGames() }
        addSource(allGames) { value = getViewPagerGames() }
    }

    private fun getRecyclerViewGames(): List<Game> {
        if (searchActive.value == true) return searchResult.value ?: emptyList()
        allGames.value?.let { if (it.size > 3) return it.subList(3, it.size) }
        return emptyList()
    }

    private fun getViewPagerGames(): List<Game> {
        if (!searchActive.value!!) allGames.value?.let { return it.subList(0, minOf(3, it.size)) }
        return emptyList()
    }

}
