package com.jazim.pixelnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jazim.pixelnews.domain.model.ShortCoin
import com.jazim.pixelnews.domain.repository.CoinRepository
import com.jazim.pixelnews.presentation.coins.CoinViewModel
import com.jazim.pixelnews.presentation.state.ShortCoinState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExperimentalCoroutinesApi
class CoinViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var coinViewModel: CoinViewModel
    private lateinit var coinRepository: CoinRepository

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        coinRepository = mockk(relaxed = true)
        coinViewModel = CoinViewModel(coinRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenGetCoinsSucceedsReturnsSuccess() = runTest(UnconfinedTestDispatcher()) {

        val mockResponse = listOf(ShortCoin("1","foobar","baz"), ShortCoin("2","barfoo","bazeggs"))
        coEvery { coinRepository.getCoins() } returns Result.success(mockResponse)

        coinViewModel.getAllCoins()

        assertEquals(false, coinViewModel.allCoinsState.value.loading)
        assertNull(coinViewModel.allCoinsState.value.error)

        val expectedState = listOf(ShortCoinState("1","foobar","baz"), ShortCoinState("2","barfoo","bazeggs"))
        assertEquals(expectedState, coinViewModel.allCoinsState.value.coins)

    }

    @Test
    fun whenGetCoinsFailsReturnsFailure() = runTest(UnconfinedTestDispatcher()) {
        val errorMessage = "Failed to load coins"
        coEvery { coinRepository.getCoins() } returns Result.failure(Exception(errorMessage))

        coinViewModel.getAllCoins()
        advanceUntilIdle()

        assertEquals(false, coinViewModel.allCoinsState.value.loading)
        assertEquals(errorMessage, coinViewModel.allCoinsState.value.error)
        assertEquals(emptyList<String>(), coinViewModel.allCoinsState.value.coins)
    }

    @Test
    fun coinsAreSortedAlphabetically() = runTest(UnconfinedTestDispatcher()) {
        val mockResponse = listOf(
            ShortCoin("1", "beta", "b"),
            ShortCoin("2", " alpha", "a"), // with a space at the start
            ShortCoin("3", "delta", "d"),
            ShortCoin("4", "gamma", "g"),
            ShortCoin("5", "epsilon", "e"),
            ShortCoin("6", "zeta", "z"),
            ShortCoin("7", "\$symbol", "!"),
            ShortCoin("8", "eta", "h"),
            ShortCoin("9", "iota", "i"),
            ShortCoin("10", "kappa", "k"),
            ShortCoin("11", "1hash", "1"),
            ShortCoin("12", " omega", "o"), // another leading space
            ShortCoin("13", "@at", "@"),
            ShortCoin("14", "theta", "t"),
            ShortCoin("15", "APPLE", "A"),
            ShortCoin("17", "apple", "a"),
            ShortCoin("16", "GARY", "G")
        )
        coEvery { coinRepository.getCoins() } returns Result.success(mockResponse)

        val alphabeticallySortedCoins = coinViewModel.getCoinsAlphabetically()

        assertEquals(
            alphabeticallySortedCoins,
            listOf(
                ShortCoin("2", " alpha", "a"),
                ShortCoin("12", " omega", "o"),
                ShortCoin("7", "\$symbol", "!"),
                ShortCoin("11", "1hash", "1"),
                ShortCoin("15", "APPLE", "A"),
                ShortCoin("16", "GARY", "G"),
                ShortCoin("17", "apple", "a"),
                ShortCoin("13", "@at", "@"),
                ShortCoin("1", "beta", "b"),
                ShortCoin("3", "delta", "d"),
                ShortCoin("5", "epsilon", "e"),
                ShortCoin("8", "eta", "h"),
                ShortCoin("4", "gamma", "g"),
                ShortCoin("9", "iota", "i"),
                ShortCoin("10", "kappa", "k"),
                ShortCoin("14", "theta", "t"),
                ShortCoin("6", "zeta", "z")
            )
        )

    }
}
