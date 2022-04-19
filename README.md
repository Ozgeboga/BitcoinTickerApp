# Bitcoin Ticker

> A Simple Cryptocurrency Tracker App

## Architecture
MVVM (Model - ViewModel - View) is the design pattern used for making this app. 

## Libraries Used

* [Architecture][1] - A collection of libraries that help you design robust, testable, and
  maintainable apps.
  * [Data Binding][2] - Declaratively bind observable data to UI elements.
  * [Room][4] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][5] - is designed to store and manage UI-related data in a lifecycle conscious way.
  * [Hilt][6] - For Dependeny Injection 
  * [Navigation Component][12] - Handle everything needed for in-app navigation.
* Third party
  * [Coil][7] An image loading library for Android backed by Kotlin Coroutines.
  * [Kotlin Coroutines][8]  a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
  * [Retrofit][10] for HTTP requests


[1]: https://developer.android.com/jetpack/arch/
[2]: https://developer.android.com/topic/libraries/data-binding/
[4]: https://developer.android.com/topic/libraries/architecture/room
[5]: https://developer.android.com/topic/libraries/architecture/viewmodel
[6]: https://dagger.dev/hilt/
[7]: https://coil-kt.github.io/coil/getting_started/
[8]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[10]: https://github.com/square/retrofit
[12]: https://developer.android.com/topic/libraries/architecture/navigation/

