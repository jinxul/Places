# Places

Places is an Android App for introducing Iranian landmarks.

## Installation

Go to the [Releases](https://github.com/jinxul/Places/releases) to download the latest APK.

## Requirement

This project needs JDK 11 to compile.
You can change the JDK version with the following options:
- [changing the IDE settings.](https://stackoverflow.com/questions/30631286/how-to-specify-the-jdk-version-in-android-studio/)
- changing the JAVA_HOME environment variable.
- changing `org.gradle.java.home` in `gradle.properties`

## Tech Stack
- [Android Jetpack](https://developer.android.com/jetpack/)
  - Data Binding
  - View Binding
  - Lifecycles
  - Room
  - ViewModel
  - Navigation
- [Android KTX](https://developer.android.com/kotlin/ktx)
- [Dagger-Hilt](https://dagger.dev/hilt/)
- [Glide](https://github.com/bumptech/glide)
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [LeakCanary](https://github.com/square/leakcanary)
- [OkHttp](https://github.com/square/okhttp)
- [Retrofit](https://github.com/square/retrofit)
- [Moshi](https://github.com/square/moshi) 
- [Lottie](https://github.com/airbnb/lottie-android)

## Architecture

Places is based on Clean architecture with MVI architectural pattern and repository pattern as its design patterns.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)