package com.jazim.pixelnews.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jazim.pixelnews.domain.repository.CoinRepository
import com.jazim.pixelnews.presentation.state.AllCoinsState
import com.jazim.pixelnews.presentation.state.OneCoinState
import com.jazim.pixelnews.presentation.state.ShortCoinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
): ViewModel() {
    private val _allCoinsState = mutableStateOf(AllCoinsState())
    val allCoinsState: State<AllCoinsState> = _allCoinsState

    private val _oneCoinState = mutableStateOf(OneCoinState())
    val oneCoinState: State<OneCoinState> = _oneCoinState

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                coinRepository.getCoins()
            }

            result.fold(
                onSuccess = { coinsInfo ->
                    _allCoinsState.value = _allCoinsState.value.copy(
                        loading = false,
                        coins = coinsInfo.map { coin ->
                            ShortCoinState(
                                id = coin.id,
                                name = coin.name
                            )
                        }
                    )
                },
                onFailure = { error ->
                    _allCoinsState.value = AllCoinsState(
                        error = error.message, loading = false,
                    )
                }
            )
        }
    }

    private fun getCoin(id: String) {
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                coinRepository.getCoinById(id)
            }

            result.fold(
                onSuccess = { coinInfo ->
                    _oneCoinState.value = _oneCoinState.value.copy(
                        loading = false,
                        name = coinInfo.name,
                        logo = coinInfo.logo,
                        description = coinInfo.description,
                        links = coinInfo.links
                    )
                },
                onFailure = { error ->
                    _oneCoinState.value = OneCoinState(
                        error = error.message, loading = false,
                    )
                }
            )
        }
    }


}