# android-mvi-redux
![MVI, CleanArchitecture](https://img.shields.io/badge/Architecture-MVI,%20Clean%20Architecture-brightgreen) ![Dagger](https://img.shields.io/badge/Dagger-Hilt-brightgreen)

## Table of Contents
 - Development and Architecture
 - Features
 - Screenshots
 - Trouble Shooting
 - Reference
 - License
 
## Development and Architecture
- [MVI with Clean Architecture](https://blog.coderifleman.com/2017/12/18/the-clean-architecture/)
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started?hl=ko)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Android Architecture Component](https://developer.android.com/topic/libraries/architecture?hl=ko) - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata?hl=ko), [Room](https://developer.android.com/topic/libraries/architecture/room?hl=ko)
- [Retrofit](https://square.github.io/retrofit/), [Moshi](https://github.com/square/moshi)
- [Mockito](https://site.mockito.org/), [MockWebserver](https://github.com/square/okhttp/tree/master/mockwebserver)
- [Github Action](https://github.com/features/actions)

## Features
### UI State Management
#### Uni Directional Data Flow

### Typography
프로젝트에서 사용할 font, font-weight, font size를 미리 정의해둠.

## Screenshots

## Trouble Shooting
#### State가 무엇이고, View를 State관점으로 관리하면 어떤 장점이 있는가?
#### 왜 MVVM 아키텍처와 같이 하였는가? 근본적으로 MVI 아키텍처는 어떠한 장점을 가져다 주는가?
#### 부분 렌더링을 지원하는 방법?
#### 생성되는 파일 개수를 줄이기 위해서는 어떤 시도를 해볼 수 있을까?
#### MVI ViewModel을 테스트 하는 방법
#### 왜 Moshi를 사용하였는가?
#### Hilt를 통한 Dependency Injection을 사용하는 이유

## Reference
### Articles and Videos
#### Clean Architecture
- 🚀[Detailed Guide on Android Clean Architecture](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011)
- [Android — Retrieving Google Location using RxJava and Clean Architecture](https://medium.com/@PedroOkawa/android-retrieving-google-location-using-rxjava-and-clean-architecture-36c1017c6529)
- [How to handle exceptions with clean architecture and Firebase](https://medium.com/firebase-tips-tricks/how-to-handle-exceptions-with-clean-architecture-and-firebase-5efbc13a1d54)
- 🚀[Android: Error handling in Clean Architecture](https://proandroiddev.com/android-error-handling-in-clean-architecture-844a7fc0dc03)
- [Android Architecture Part 5: How to Test Clean Architecture](https://five.agency/android-architecture-part-5-test-clean-architecture/)
- [Clean Architecture는 모바일 개발을 어떻게 도와주는가? - (1) 경계선: 계층 나누기](https://medium.com/@justfaceit/clean-architecture%EB%8A%94-%EB%AA%A8%EB%B0%94%EC%9D%BC-%EA%B0%9C%EB%B0%9C%EC%9D%84-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%84%EC%99%80%EC%A3%BC%EB%8A%94%EA%B0%80-1-%EA%B2%BD%EA%B3%84%EC%84%A0-%EA%B3%84%EC%B8%B5%EC%9D%84-%EC%A0%95%EC%9D%98%ED%95%B4%EC%A4%80%EB%8B%A4-b77496744616)
- 🚀[Let’s design together with Clean Architecture in Android](https://medium.com/swlh/design-together-clean-architecture-android-77624cf5d385)
- [Clean Architecture — Kotlin, Dagger 2, RxJava, MVVM and Unit Testing](https://medium.com/@rahul.singh/clean-architecture-kotlin-dagger-2-rxjava-mvvm-and-unit-testing-dc05dcdf3ce6)
- [Repository layer using Room and Dagger 2 — Android](https://android.jlelse.eu/repository-layer-using-room-and-dagger-2-android-12d311830fd9)


#### MVI
- [Android Unidirectional Data Flow — Kotlin Flow vs. RxJava](https://proandroiddev.com/udf-flowvsrx-a792b946d75c)
- [Android Model-View-Intent with Kotlin Flow](https://proandroiddev.com/android-model-view-intent-with-kotlin-flow-ca5945316ec#ee30)
- [Lessons Learned Implementing Redux on Android](https://hackernoon.com/lessons-learned-implementing-redux-on-android-cba1bed40c41)
- [Unidirectional data flow on Android using Kotlin: The blog post (part 1)](https://proandroiddev.com/unidirectional-data-flow-on-android-the-blog-post-part-1-cadcf88c72f5)
- [Unidirectional data flow on Android using Kotlin: The blog post (part 2)](https://proandroiddev.com/unidirectional-data-flow-on-android-the-blog-post-part-2-b8cfedb1265a)
- [Unidirectional data flow on Android using Kotlin: The blog post (part 3)](https://proandroiddev.com/unidirectional-data-flow-on-android-using-kotlin-the-blog-post-part-3-9ae465e44afa)
- 🚀[Representing View State with Kotlin Data Classes](https://medium.com/@trionkidnapper/viewmodel-and-kotlin-data-class-7d3a3b854805)
- 🚀[MVI - The Good, the Bad, and the Ugly](https://adambennett.dev/2019/07/mvi-the-good-the-bad-and-the-ugly/)
- 🚀[[번역] 상태 지향 아키텍처 MVI를 소개합니다- MVI on Android](https://medium.com/@jaehochoe/%EB%B2%88%EC%97%AD-%EC%83%81%ED%83%9C-%EC%A7%80%ED%96%A5-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-mvi%EB%A5%BC-%EC%86%8C%EA%B0%9C%ED%95%A9%EB%8B%88%EB%8B%A4-mvi-on-android-725cae5b1753)
- [Theories behind MVC, MVP, MVVM and MVI patterns in Android](https://medium.com/@royanimesh2211/theories-behind-mvc-mvp-mvvm-and-mvi-patterns-on-android-7faebe91cca5)
- [REACTIVE APPS WITH MODEL-VIEW-INTENT - PART3 - STATE REDUCER](http://hannesdorfmann.com/android/mosby3-mvi-3)
- [REACTIVE APPS WITH MODEL-VIEW-INTENT - PART4 - INDEPENDENT UI COMPONENTS](http://hannesdorfmann.com/android/mosby3-mvi-4)
- [MVI on Android](https://medium.com/@fnberta/mvi-on-android-20677f80df55)
- [So why do we need MVI in mobile development?](https://medium.com/@seangares13/so-why-do-we-need-mvi-in-mobile-development-3bd467b29841)
- 🚀[Android MVI-Reactive Architecture Pattern](https://medium.com/@abhiappmobiledeveloper/android-mvi-reactive-architecture-pattern-74e5f1300a87)
- [YouTube - BADG: Making Android reactive apps with MVI](https://www.youtube.com/watch?v=Mlr4U55FKU4)
- [YouTube - How to cook a well-done MVI for Android](https://www.youtube.com/watch?v=Ls0uKLqNFz4)
- 🚀[YouTube - [드로이드나이츠 2020] 최재호 - MVI 아키텍처 적용기](https://www.youtube.com/watch?v=_ePUpzECd8c&t=1187s)
- [Créer une application Android en utilisant le pattern MVI et Kotlin Coroutines](https://blog.engineering.publicissapient.fr/2020/02/10/mvi-creer-une-application-android-en-utilisant-le-pattern-mvi-et-kotlin-coroutines/)
- 🚀[Getting started with MVI Architecture on Android](https://proandroiddev.com/getting-started-with-mvi-architecture-on-android-b2c280b7023)

#### Hilt
- [Dependency Injection in Android with Hilt: First Impression](https://medium.com/@amritlalsahu5/dependency-injection-in-android-with-hilt-41fb915997e4)
- [Dagger - Hilt 간보기](https://two22.tistory.com/4)
- [Dagger Hilt로 안드로이드 의존성 주입 시작하기](https://hyperconnect.github.io/2020/07/28/android-dagger-hilt.html)
- [YouTube - [드로이드나이츠 2020] 옥수환 - Hilt와 함께 하는 안드로이드 의존성 주입](https://www.youtube.com/watch?v=gkUCs6YWzEY)
- [Hilt — The Future of Dependency Injection in Android](https://levelup.gitconnected.com/hilt-the-future-of-dependency-injection-in-android-e9a919c0993d)

#### Git
- [좋은 Readme 작성법](https://always0ne.github.io/github/Readme/)
- 🚀[awesome-readme](https://github.com/matiassingers/awesome-readme)
- [Android Github Actions #1](https://medium.com/@lee199402/android-github-actions-1-fd4754fa6b19)
- [Github Action 을 이용한 CI 구축하기](https://dublin-java.tistory.com/65)
- [Accessing an Android app secret from GitHub Actions using Gradle](https://blog.jakelee.co.uk/accessing-android-app-secret-from-github-actions-using-gradle/)

### Repos
- [MVI-Architecture-Android-Beginners](https://github.com/MindorksOpenSource/MVI-Architecture-Android-Beginners)
- [TvFlix](https://github.com/reactivedroid/TvFlix)
- [google-location-rx](https://github.com/PedroOkawa/google-location-rx)
- 🚀[uniflow-kt](https://github.com/uniflow-kt/uniflow-kt)
- 🚀[weatherapp-uniflow](https://github.com/uniflow-kt/weatherapp-uniflow)
- [android-mvi-sample](https://github.com/kanawish/android-mvi-sample)
- 🚀[android-clean-architecture-mvi-boilerplate](https://github.com/bufferapp/android-clean-architecture-mvi-boilerplate)
- [spotify - mobius](https://github.com/spotify/mobius)
- [RxRedux](https://github.com/freeletics/RxRedux)
- 🚀[ywett02 - CountriesMVI](https://github.com/ywett02/CountriesMVI)
- 🚀[Tinder - StateMachine](https://github.com/Tinder/StateMachine)
- [badoo - MVICore](https://github.com/badoo/MVICore)
- 🚀[MyRealTrip - box](https://github.com/myrealtrip/box)
- 🚀[airbnb - MvRx](https://github.com/airbnb/MvRx)
- [Baking-App-Kotlin](https://github.com/Ezike/Baking-App-Kotlin)
- 🚀[mozilla-firefox-fenix](https://github.com/mozilla-mobile/fenix)
- [MVVM-Kotlin-Android-Architecture](https://github.com/ahmedeltaher/MVVM-Kotlin-Android-Architecture)
```
MIT License

Copyright (c) 2020 Lee Oh Hyung

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
