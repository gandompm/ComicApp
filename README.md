# ComicApp

ComicApp is an interactive mobile application that allows users to explore and favorite their favorite comics. 
It leverages modern technologies such as Jetpack Compose, Dagger Hilt, Retrofit, Room local database, Coil, Coroutines, and instrumented unit tests for local database.



Features:

Explore Comics: Dive into a vast collection of comics and browse through them effortlessly. Each comic is presented with its title, number, date, and an image.
Favorite Comics: Mark your favorite comics and easily access them later in the Favorites section.
Comic Details: View detailed information about a comic, including its alt text, transcript, and publication date.
Text-to-Speech: Listen to the transcript of a comic using the built-in Text-to-Speech functionality.
Random Comic: Discover a random comic with a single tap, perfect for a surprise laugh or inspiration.
Navigation: Smooth navigation within the app with a bottom navigation bar for easy access to different sections.

Key Technologies:

Jetpack Compose: The app is built using Jetpack Compose, the modern UI toolkit for building native Android apps, providing a declarative and efficient way to create beautiful user interfaces.
Dagger Hilt: Dependency injection framework that ensures clean and modular code architecture, making it easier to manage dependencies and promote testability.
Retrofit: A type-safe HTTP client for making API requests to retrieve comic data from a remote server.
Room Database: Utilizes the Room persistence library to store favorite comics locally, enabling offline access and efficient data retrieval.
Coil: An image loading library for fetching and displaying comic images seamlessly.
Coroutines: Asynchronous programming library used to handle network requests and database operations in a structured and efficient manner.
Instrumented Unit Tests: Comprehensive unit tests are implemented to ensure the reliability and correctness of the local database functionality.

By combining the power of these technologies, ComicApp delivers a delightful user experience, allowing users to explore, favorite, and enjoy their favorite comics easily. The clean architecture pattern, MVVM (Model-View-ViewModel), along with the use of use cases, ensures separation of concerns, testability, and maintainability of the codebase.
