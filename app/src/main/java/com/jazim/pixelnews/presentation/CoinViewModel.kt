package com.jazim.pixelnews.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.util.fastMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jazim.pixelnews.domain.repository.CoinRepository
import com.jazim.pixelnews.presentation.state.CoinsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
): ViewModel() {
    private val _coinState = mutableStateOf(CoinsState())

    val coinState: State<CoinsState> = _coinState

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            val startTime = System.currentTimeMillis() // Start timer

            val result = withContext(Dispatchers.IO) {
                coinRepository.getCoins()
            }
            val endTime = System.currentTimeMillis() // End timer
            println("getCoins() took ${endTime - startTime}ms")

            result.fold(
                onSuccess = { coinInfo ->
                    _coinState.value = _coinState.value.copy(
                        loading = false,
                        coinNames = coinInfo.fastMap { it.name }
                    )
                },
                onFailure = { error ->
                    _coinState.value = CoinsState(
                        error = error.message, loading = false,
                    )
                }
            )

        }
    }
}