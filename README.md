# credit-score-donut 

credit-score-donut is a multiplatform mobile application built with Compose Multiplatform Mobile (CMP) that fetches and displays credit score information from the CS API. This project demonstrates the use of Clean Architecture, Compose Multiplatform, Koin, Ktor, Kotlin Coroutines, and Flow to create a well-structured, testable, and reactive application.

## Features

*   Fetches credit score data from the CS API.
*   Displays credit score information in a user-friendly format.
*   Handles loading, success, and error states gracefully.
*   Uses Clean Architecture for a well-organized codebase.
*   Built with Kotlin Multiplatform Mobile (KMM) for code sharing across platforms.
*   Utilizes Jetpack Compose for a modern declarative UI.
*   Employs Koin for dependency injection.
*   Uses Ktor for network requests.
*   Leverages Kotlin Coroutines and Flow for asynchronous and reactive programming.
*   Includes unit tests for the `HomeViewModel`.


## Architecture

credit-score-donut follows the principles of Clean Architecture, which divides the application into distinct layers:

*   **Domain Layer:**
    *   Contains core business logic and entities.
    *   Defines the `CreditInfo`, `CreditReportInfo`, and `RequestState` data models.
    *   Includes the `CreditInfoRepository` interface, which defines the contract for fetching credit information.
*   **Data Layer:**
    *   Handles data access and communication with external sources.
    *   Includes the `ClearScoreApiService` interface and its implementation (`ClearScoreApiServiceImpl`) for network requests.
    *   Implements the `CreditInfoRepository` interface (`CreditInfoRepositoryImpl`).
*   **Presentation Layer:**
    *   Contains the UI and UI-related logic.
    *   Includes the `HomeScreen` (Jetpack Compose UI) and the `HomeViewModel`.
    *   The `HomeViewModel` interacts with the `CreditInfoRepository` to fetch and manage credit information.

## Technical Decisions

*   **Clean Architecture:** The decision to use Clean Architecture was made to ensure a clear separation of concerns, making the code more maintainable, testable, and scalable.
*   **Repository Pattern:** The repository pattern was chosen to abstract data access, allowing for easy switching of data sources and mocking for testing.
*   **Koin for Dependency Injection:** Koin was selected for its simplicity and lightweight nature, making it a good fit for KMM projects.
*   **Ktor for Networking:** Ktor was chosen for its multiplatform support and its ability to handle asynchronous network requests efficiently.
*   **Kotlin Coroutines and Flow:** These were chosen for their ability to handle asynchronous operations and reactive data streams in a concise and readable way.
*   **JUnit** chosen for unit testing.

## Installation & Setup

* Android Studio Giraffe+
* Xcode (for iOS development)
* Kotlin Multiplatform plugin

## Screenshots

Android:

<img width="224" alt="Image" src="https://github.com/user-attachments/assets/15127b21-c88c-491b-8214-a014ca06231f" />

iOS:

<img width="347" alt="Image" src="https://github.com/user-attachments/assets/e2606a12-41a3-4a60-9a58-bdb9babe357a" />

## Libraries 

• Koin: Dependency Injection (DI) framework for Kotlin.

• Ktor: Multiplatform asynchronous HTTP client and server framework.

• Voyager: Navigation library for Jetpack Compose.

• Kotlinx Serialization: Kotlinx Serialization is a powerful, multiplatform, and multi-format serialization library for Kotlin.

• Kotlinx DateTime: Kotlinx DateTime is a multiplatform library for working with dates, times, and time zones in Kotlin

• Stately: Multiplatform state management library.

