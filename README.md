# Movie Explorer - Kotlin Multiplatform

Movie Explorer is a Kotlin Multiplatform Mobile (KMM) application that allows users to search and view movie information
from the OMDb API.
It showcases a clean and maintainable architecture using Clean Architecture + MVVM, with shared business logic and UI
components written in Kotlin Multiplatform and platform-specific UIs built with Jetpack Compose (Android) and SwiftUI (
iOS).

## Project Overview

This application provides a seamless movie browsing experience across Android and iOS platforms. Users can search for
their favorite movies through an intuitive search interface that queries the OMDb API in real-time. The search results
are displayed in an elegant list format, showing movie posters, titles, and release dates. When a user taps on any movie
from the list, they're taken to a detailed view that presents comprehensive information including the full plot,
director, and IMDb rating.

- Search movies by title through an intuitive search interface powered by the OMDb API.

- View detailed movie information, including poster, title, release date, director, plot, and IMDb rating.

- Built with 100% shared business logic and domain code, ensuring consistent behavior across both platforms.

- Uses manual dependency injection for maximum transparency and testability.

## Project Structure

The project is organized following Clean Architecture principles, which promotes a clear separation of concerns and
makes the codebase highly maintainable and testable. The architecture is divided into four distinct layers, each with
its own responsibility and level of abstraction.

The application follows a layered architecture where dependencies flow inward, with the domain layer at the center being
completely independent of external frameworks and libraries. This design ensures that the core business logic remains
pure and testable, while infrastructure and presentation details are kept at the outer layers.

| Layer              | Responsibility                                                   | Contains                                                 |
|--------------------|------------------------------------------------------------------|----------------------------------------------------------|
| **Domain**         | Core business logic, independent from frameworks                 | Entities, interfaces, and use cases                      |
| **Application**    | Coordinates business logic and exposes use cases to presentation | DTOs, mappers, use case implementations                  |
| **Infrastructure** | Handles data access and external communication                   | API clients, data sources, models                        |
| **Presentation**   | Manages UI state and navigation                                  | ViewModels, UI state models, and platform-specific views |

## Testing

Unit tests are implemented using the kotlin.test framework.
To run all tests:

```bash
./gradlew :composeApp:allTests
```

Tests focus on:

- Business logic and use cases in the domain layer.
- API and data source logic in the infrastructure layer.
- ViewModel state management in the presentation layer.

## Getting Started

Follow these steps to set up, configure, and run the project on your local environment.

### 1. Prerequisites

Before running the project, make sure your development environment is ready for Kotlin Multiplatform development:

- Android Studio (latest stable version) or IntelliJ IDEA Ultimate with the Kotlin Multiplatform plugin
- Xcode (for iOS development)
- Gradle (handled automatically by Android Studio)
- A valid OMDb API key

### 2. Environment Configuration

The project requires an environment configuration file to securely store API credentials and runtime constants.
In the commonApp module, you’ll find an example file named: `environment.example.kt`

Use this as a template to create your own environment.kt file in the same directory:

```kt
object AppEnvironment {
    const val API_KEY = "your_api_key_here"
}
```

The AppEnvironment object is used by the DependencyContainer to configure the API client and authenticate all requests
to the OMDb API.

Once created, make sure to add environment.kt (and any file containing your API key) to your .gitignore file to avoid
exposing sensitive information.

### 3. Building and Running the Project

To build and run the Android version of the app, use one of the following methods:

- From Android Studio:
  Select the composeApp run configuration and click Run ▶️

- From the terminal:

```# macOS / Linux
./gradlew :composeApp:assembleDebug

# Windows
.\gradlew.bat :composeApp:assembleDebug
```

The app will be available in your emulator or connected Android device.

- To build and run the iOS version:
- Open the /iosApp directory in Xcode
- Select your target device or simulator
- Run the app using the Run ▶️ button in the Xcode toolbar

Xcode will automatically build the Kotlin Multiplatform framework and launch the SwiftUI application.


