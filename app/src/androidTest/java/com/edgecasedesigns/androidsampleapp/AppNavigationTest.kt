package com.edgecasedesigns.androidsampleapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeViewModel
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeScreen
import com.edgecasedesigns.androidsampleapp.ui.screen.detail.DetailScreen
import org.junit.Rule
import org.junit.Test

class AppNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigateFromHomeToDetailAndBack() {
        val fakeRepo = FakeItemRepository()
        val viewModel = HomeViewModel(fakeRepo)

        lateinit var navController: NavHostController

        composeTestRule.setContent {
            navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreen(navController, viewModel)
                }
                composable(
                    "detail/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) {
                    val id = it.arguments?.getInt("id") ?: 0
                    DetailScreen(navController, id, viewModel)
                }
            }
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("Test Title 1").fetchSemanticsNodes().isNotEmpty()
        }

        // Navigate to Detail
        composeTestRule.onNodeWithText("Test Title 1").performClick()
        composeTestRule.onNodeWithText("Test Content 1").assertIsDisplayed()

        // Simulate back press by navigating back
        composeTestRule.runOnIdle {
            navController.popBackStack()
        }

        // Verify we are back on Home screen
        composeTestRule.onNodeWithText("Test Title 1").assertIsDisplayed()
    }
}
