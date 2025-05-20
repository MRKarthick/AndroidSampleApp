package com.edgecasedesigns.androidsampleapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeScreen
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule() // Not AndroidComposeRule

    private val fakeRepository = FakeItemRepository()

    @Test
    fun homeScreen_showsItemsAndNavigatesToDetail() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel = HomeViewModel(fakeRepository)

            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("Test Title 1").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("Test Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Subtitle 1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Test Title 1").performClick()
    }
}