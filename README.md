## Android Sample App

This is a simple Android app built with Jetpack Compose and MVVM architecture. It fetches remote JSON data from a GitHub-hosted URL and displays it in a list. Users can tap on each item to view detailed information. The app supports pull-to-refresh and is designed with clean architecture and modular code.

##  Setup Instructions

1. Clone this repository.
2. Open the project in Android Studio
3. Let Gradle sync all dependencies.
4. Run the app on an emulator or a physical device.

## Features

- Loads JSON data from a remote GitHub URL using Retrofit and Moshi.
- Displays the list in a clean Compose UI.
- Supports item click navigation to a detail screen using Navigation Compose.
- Implements MVVM architecture using ViewModel.
- Supports pull-to-refresh.
- UI components include @Preview annotations for fast iteration.

## Architecture

- ViewModel: Exposes UI state
- Repository: Abstracts data fetching logic from Retrofit.
- UI: Built with Jetpack Compose using state hoisting.
- Navigation: Managed using Navigation Compose with a single activity architecture.

## Testing

- Unit tests for HomeViewModel using a fake repository.
    - Verifies loading state transitions.
    - Verifies refresh logic and data updates.
- Navigation test using composeTestRule:
    - Simulates tapping a list item and verifies navigation to the detail screen.
    - Simulates back navigation and verifies return to the home screen.

## Previews

- All major composables include @Preview support.
- Verified for both light and dark modes.
- Previews tested with dynamic data and various screen widths.

