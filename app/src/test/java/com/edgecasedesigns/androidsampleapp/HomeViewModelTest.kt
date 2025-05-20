import com.edgecasedesigns.androidsampleapp.data.model.Item
import com.edgecasedesigns.androidsampleapp.data.remote.ItemRepository
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `initial state shows loading then loads items`() = runTest {
        val viewModel = HomeViewModel(FakeRepository())

        assertTrue(viewModel.isLoading)
        advanceUntilIdle()

        assertFalse(viewModel.isLoading)
        assertEquals(2, viewModel.items.size)
    }

    @Test
    fun `refresh updates items and isRefreshing flag`() = runTest {
        val viewModel = HomeViewModel(FakeRepository())
        advanceUntilIdle()

        viewModel.refresh()
        assertTrue(viewModel.isRefreshing)
        advanceUntilIdle()

        assertFalse(viewModel.isRefreshing)
        assertEquals(2, viewModel.items.size)
    }
}

class FakeRepository : ItemRepository {
    override suspend fun getItems(): List<Item> {
        return listOf(
            Item(1, "Title 1", "Subtitle 1", "Content 1"),
            Item(2, "Title 2", "Subtitle 2", "Content 2")
        )
    }
}