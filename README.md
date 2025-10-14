# Movie Explorer - Kotlin Multiplatform

A Kotlin Multiplatform mobile application for searching and exploring movies using the OMDb API. This project demonstrates Clean Architecture principles with MVVM pattern, built entirely with KMP for both Android and iOS platforms.
## Project Overview

This application provides a seamless movie browsing experience across Android and iOS platforms. Users can search for their favorite movies through an intuitive search interface that queries the OMDb API in real-time. The search results are displayed in an elegant list format, showing movie posters, titles, and release dates. When a user taps on any movie from the list, they're taken to a detailed view that presents comprehensive information including the full plot, director, and IMDb rating.

The entire application is built using Kotlin Multiplatform, sharing 100% of the business logic and UI code between platforms. This approach ensures consistency in behavior and appearance while maintaining native performance on both Android and iOS devices.

## Project Structure
The project is organized following Clean Architecture principles, which promotes a clear separation of concerns and makes the codebase highly maintainable and testable. The architecture is divided into four distinct layers, each with its own responsibility and level of abstraction.

The application follows a layered architecture where dependencies flow inward, with the domain layer at the center being completely independent of external frameworks and libraries. This design ensures that the core business logic remains pure and testable, while infrastructure and presentation details are kept at the outer layers.

## Dependencies and Dependency Management
Rather than using a dependency injection framework like Koin or Kodein, the project implements a simple but effective manual dependency injection pattern through the DependencyContainer object.

## Network Communication
Since external networking libraries like Ktor or Retrofit are not permitted, the project implements its own HTTP client abstraction using Kotlin Multiplatform's expect/actual mechanism:

## Getting Started
To run this project, you'll need a development environment properly configured for Kotlin Multiplatform development.

Before running the application, you need to configure your OMDb API key:

- Visit OMDb API to obtain a free API key
- Create AppEnvironment.kt in the common source set:

```kt
object AppEnvironment {
const val API_KEY = "your_api_key_here"
}
```
- Add AppEnvironment.kt to .gitignore to protect your API key

The dependency container references this API key when creating the API client, ensuring all requests are properly authenticated.

## Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget in your IDE’s toolbar or build it directly from the terminal: - on macOS/Linux

```shell
./gradlew :composeApp:assembleDebug
```
- on Windows
```
  shell
  .\gradlew.bat :composeApp:assembleDebug
 ```
## Build and Run iOS Application 
To build and run the development version of the iOS app, use the run configuration from the run widget in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.
