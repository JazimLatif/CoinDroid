package com.jazim.pixelnews.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jazim.pixelnews.domain.repository.CoinRepository
import com.jazim.pixelnews.presentation.state.AllCoinsState
import com.jazim.pixelnews.presentation.state.CoinDetailState
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

    private val _coinDetailState = mutableStateOf(CoinDetailState())
    val coinDetailState: State<CoinDetailState> = _coinDetailState

    init {
        getAllCoins()
    }
    fun getAllCoins() {
        Log.d("API_CALL", "getAllCoins called at ${System.currentTimeMillis()}")  // Already added

        // I initially thought this line wasn't needed because loading is true by default in AllCoinsState()
        // but when called again on pull down to refresh, it wasn't working until this line was added since loading was false after initial load
        _allCoinsState.value = AllCoinsState(loading = true, error = null)


        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                coinRepository.getCoins()
            }

            result.fold(
                onSuccess = { coinsInfo ->
                    _allCoinsState.value = AllCoinsState(
                        loading = false,
                        coins = coinsInfo.map { coin ->
                            ShortCoinState(
                                id = coin.id,
                                symbol = coin.symbol,
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

    fun getCoin(id: String) {

        _coinDetailState.value = CoinDetailState(loading = true, error = null)
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                coinRepository.getCoinById(id)
            }

            result.fold(
                onSuccess = { coinInfo ->
                    _coinDetailState.value = CoinDetailState(
                        loading = false,
                        name = coinInfo.name,
                        logo = coinInfo.logo,
                        description = coinInfo.description,
                        links = coinInfo.links
                    )
                },
                onFailure = { error ->
                    _coinDetailState.value = CoinDetailState(
                        error = error.message, loading = false,
                    )
                }
            )
        }
    }

    // If there were a lot of these "helper" functions they could be moved into a class and injected, since there aren't many I chose to leave them here

    fun getCoinsAlphabetically(): List<ShortCoinState>{
        return _allCoinsState.value.coins.map { coin ->
            ShortCoinState(
                id = coin.id,
                name = coin.name,
                symbol = coin.symbol
            )
        }.sortedBy { it.name }
    }
}