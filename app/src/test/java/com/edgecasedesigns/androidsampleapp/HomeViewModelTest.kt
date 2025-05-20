package com.edgecasedesigns.androidsampleapp

import com.edgecasedesigns.androidsampleapp.data.model.Item
import com.edgecasedesigns.androidsampleapp.data.remote.Repository
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(FakeRepository())
    }

    @Test
    fun `initial state shows loading then loads items`() = runTest {
        assertTrue(viewModel.isLoading)
        advanceUntilIdle()
        assertFalse(viewModel.isLoading)
        assertEquals(2, viewModel.items.size)
    }

    @Test
    fun `refresh updates items and isRefreshing flag`() = runTest {
        advanceUntilIdle()
        viewModel.refresh()
        assertTrue(viewModel.isRefreshing)
        advanceUntilIdle()
        assertFalse(viewModel.isRefreshing)
        assertEquals(2, viewModel.items.size)
    }
}

class FakeRepository : Repository() {
    override suspend fun getItems(): List<Item> {
        return listOf(
            Item(1, "Title 1", "Subtitle 1", "Content 1"),
            Item(2, "Title 2", "Subtitle 2", "Content 2")
        )
    }
}