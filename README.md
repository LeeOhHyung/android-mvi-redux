# android-mvi-redux
![MVI, CleanArchitecture](https://img.shields.io/badge/Architecture-MVI,%20Clean%20Architecture-brightgreen) ![Dagger](https://img.shields.io/badge/Dagger-Hilt-brightgreen)

## Table of Contents
 - Development and Architecture
 - Features
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
#### 1. Clean Architecture
<p>
 <img src="https://user-images.githubusercontent.com/37705123/97113823-16018700-1730-11eb-8f79-8887cc22126b.png" width="400" />
 <img src="https://user-images.githubusercontent.com/37705123/97113826-1863e100-1730-11eb-8c7c-e588244014ca.png" width="400" />
</p>
Presentation - Data - Domain 3개의 Layer로 구성되어 있고, Data Layer는 다시 Remote, Local Layer로 분리하였습니다.  

#### 2. MVI Architecture - similar to Redux
<p>
 <img src="https://user-images.githubusercontent.com/37705123/97111060-037f5180-1720-11eb-8656-af4d48c889e8.png" width="400" />
</p> 
위 그림을 토대로 프로젝트의 구조를 설계 하였습니다.
가장 중요한 개념은, 사용자가 화면을 클릭하는 행위 - 버튼클릭, 토글조작, 스크롤링 등등 - 를 `객체를 발행하는 행위`라고 규정하는 것입니다. 그리고, 사용자가 객체를 발행하는 행위가 기본적으로 앱의 상태를 변화시키고자 하는 의도를 나타낸다고 규정하고 이것을 `Intent` 라는 용어를 사용합니다.

<p>
 <img src="http://hannesdorfmann.com/images/mvi/mvi-func2.png" width="600" /> 
</p>

```
// Intent 에서 부터 render 함수 호출까지 흐름
Intent -> IntentProcessor -> Action -> ActionProcessor -> Result -> Reducer -> newState -> render
```
View 에서 입력받은 Intent는 ViewModel로 전달되는데, IntentProcessor 부터 Reducer 까지의 행위를 `StateMachine` 라는 위임자를 두어서 한 곳에서 관리하도록 설계 하였습니다.
주요 포인트의 설명을 추가하자면, 
- IntentProcessor는 사용자 상호작용으로 생성한 Intent 객체를 Action으로 변환 해주는 작업을 수행
- Action 객체는 실제로 앱이 수행해야할 동작들을 구분해서 정의한 클래스
- ActionProcessor는 StateMachine의 핵심 역할로써, UseCase를 실행시켜 Remote, Local 에서 데이터를 가져오는 동작이 실제로 실행되는 곳이며, 작업 실행의 결과를 Result로 변환시키고, Reducer로 전달하는 역할을 수행
- Reducer는 전달받은 Result가 무엇인지에 따라서 새로운 State를 생성하는 역할

```kotlin
// HomeViewState
data class HomeViewState(
  val isLoading: Boolean,
  val forecast: Forecast,
  val error: Throwable?
) : ViewState

// HomeViewModel
class HomeViewModel @ViewModelInject constructor(
  private val stateMachine: HomeStateMachine
) : MviViewModel<HomeViewIntent, HomeViewAction, HomeViewState, HomeViewResult>(stateMachine) {

    override val viewState: LiveData<HomeViewState>
        get() = stateMachine.currentState
}

// HomeActionProcessor
class HomeActionProcessor @Inject constructor(
  private val getCurrentLocationForecastUseCase: GetCurrentLocationForecastUseCase,
  private val executorProvider: ExecutorProvider
) : ActionProcessor<HomeViewAction, HomeViewResult>
```

#### 3. Typography
font, font-weight, font size를 미리 정의해두고, TextView, EditText 등과 같이 Text속성이 필요한 곳에서 요긴하게 사용할 수 있도록 합니다.
```xml
// 모든 Typography style에 공통 적용될 폰트, 색상, 속성 등을 정의
<style name="Typography">
    <item name="android:fontFamily">@font/roboto_regular</item>
    <item name="android:textColor">@android:color/black</item>
    <item name="android:includeFontPadding">false</item>
</style>

// Font-weight에 따라서 fontSize, textStyle 정의
<style name="Typography.400">
    <item name="android:textSize" tools:ignore="SpUsage">@dimen/text_size16</item>
    <item name="android:textStyle">normal</item>
</style>

// Thin, Light, Medium, Bold 등 폰트에 따라 정의
<style name="Typography.400.Thin">
    <item name="fontFamily">@font/roboto_thin</item>
</style>
<style name="Typography.400.Light">
    <item name="fontFamily">@font/roboto_light</item>
</style>
<style name="Typography.400.Medium">
    <item name="fontFamily">@font/roboto_medium</item>
</style>
<style name="Typography.400.Bold">
    <item name="fontFamily">@font/roboto_bold</item>
</style>
```

## Trouble Shooting
#### 1. State가 무엇이고, View를 State관점으로 관리하면 어떤 장점이 있는가?
[State](https://en.wikipedia.org/wiki/State_(computer_science))는 View/Application를 구성하기 위한 데이터 또는 행위들의 집합 이라고 표현 할 수 있습니다. State만 보면 현재 View의 모습과 데이터를 알 수 있고. 또한 상태 충돌을 피하기 위해서 불변하는 데이터구조를 가집니다.

State 관점으로 설계하는 디자인 패턴으로는 [상태패턴](https://refactoring.guru/design-patterns/state)이 있는데, 각각의 상태를 객체로 정의해서 조건문 분기처리가 아닌 상태객체 전달만으로도 View의 데이터를 갱신 시킬 수 있습니다. 또한, 상태객체에 행위를 위임함으로써 상태 변화를 쉽게 적용할 수 있다는 장점이 있습니다.
만약, View의 상태를 추가해야한다면, 손쉽게 상태 객체를 추가할수 있으며, 상태 객체들은 모두 같은 인터페이스(혹은 추상클래스)를 상속받고 있기때문에 간편하게 확장시킬 수 있습니다.

주의 해야할 점 중 하나는, 각각의 상태는 불변객체로 정의하고, 새로 만들어진 상태와 이전 상태를 구분하기 위해서 새로운 상태객체를 발행할때는 반드시 새로운 객체를 반환해야한다는 점입니다.

#### 2. 근본적으로 MVI 아키텍처는 어떠한 장점을 가져다 주는가? 혹은 단점은 무엇이 있을까?
눈치가 빠르다면, 이미 짐작했겠지만, Android-MVI 아키텍처는 [React-redux](https://redux.js.org/)와 상당히 유사하다. 참고하기 바랍니다.
MVI 아키텍처에서는 단방향 데이터 흐름(Uni-Directional Data Flow), 상태 불변성을 장점으로 내세우고 있습니다.

사용자로 부터 View Intent를 받고, `IntentProcessor -> ActionProcessor -> Reducer -> new State` 과정에서 데이터가 단방향으로만 흐르기 때문에, 확실히!! 앱의 로직흐름을 명확하게 이해할 수 있다는 장점이 있습니다. 마찮가지로 각자의 구성요소만 별도로 테스트 하면 되기 떄문에, 문제가 발생했을때 빠르게 디버깅할 수 있습니다.

항상 장점만 있는것은 아니죠. MVI 아키텍처를 구현하기 위해서는 Intent, Action, Reducer, Processor 등등 많은 파일과 클래스를 만들어야 합니다. 아키텍처를 철저히 지키기 위해서는 반드시 이루어져야 하는 과정이지만, 많은 파일과 클래스는 그만큼 관리 포인트가 높아지는 효과를 가져옵니다.
또한, 아직 명확하게 어떤 단계에서 구현해야 하는지 고민해야하는 부분들이 있는데요. 예를 들면, App Navigation, Toast, SnackBar 같은 일회성 ViewEvent에 대해서도 MVI-StateMachine 사이클을 모두 순회하고, 새로운 상태객체를 발행해서 처리 해야하는지, View에서 바로 처리해도 되는지 등등에 대한 문제입니다. 그리고, 페이지네이션을 구현할때도 매우 골치가 아픕니다. 왜냐하면, 페이지네이션은 다른 상태값은 모두 같고, 새로 가지고 오는 List만 추가 되는 형태로 구현됩니다. 하지만, 이 과정에서 상태가 새롭게 발행되고, 화면의 모든 구성요소가 새롭게 그려지는 현상이 발생했습니다. 

#### 3. 부분 렌더링을 지원하는 방법?
먼저, 부분렌더링을 왜 지원해야하는지 고민해야할 필요가 있습니다. 아래와 같은 ViewState가 있다고 가정하고 설명을 이어나가도록 하겠습니다.
```kotlin
data class ViewState(
  val isLoading: Boolean,
  val title: String,
  val username: String,
  val photos: List<Photo>,
  val error: Throwable?
)
```
만약, `photos`의 데이터가 변경되어서 새로운 상태 객체가 발행될때, `render` 함수에서는 title, username 등등은 데이터가 변경되지 않았음에도 화면을 다시 그리는 문제가 발생합니다. 매우 불쾌한 사용자 경험을 줄 수 가 있는데, 페이지네이션을 해서 새로운 리스트를 계속 가지고 오는 상황에서 매번 스크롤이 상단으로 이동하게 될 것입니다.

###### 1. 값이 같으면 렌더링하지 않음.
```kotlin
fun render(oldState: ViewState, newState: ViewState) {
  if(oldState.title != newState.title) {
    tv_title.text = newState.title
  }
  if(oldState.username != newState.username) {
    tv_username.text = newState.uername
  }
}
```
이것은 매우 간단하게 적용할 수 있겠지만, 항상 이전의 상태를 같이 전달해야 하고, 분기 처리 코드가 많아 가독성이 떨어질 수 있습니다.

###### 2. [RenderingScope](https://github.com/myrealtrip/box/blob/master/box/src/main/kotlin/com/mrt/box/android/BoxRenderingScope.kt)을 사용하는 방법.
```kotlin 
// MyRealTrip - Box 프레임워크에서 사용하는 방법
override val partialRenderers: Map<BoxRenderingScope, BoxRenderer>? = mapOf(
  ProgressScope to ProgressRenderer,
  ColorScope to ColorRenderer
)

override fun render(state: ViewState) {
  partialRenderers?.forEach {
    if (state.scope() == it.key || if(state is ScopeState) state.scopes().contains(it.key) else false) {
      it.value.renderView(this, state, vm)
    }
  }
}
```
렌더링 해야하는 View는 여러 구역으로 나누어서, 필요한 부분부분만 업데이트 하도록 하는 방식입니다. 부분렌더링 지원은 확실하게 할 수 있겠지만, Scope는 어떤 기준으로 나누어야 하는지와 Scope파일 관리 하는 방법 등에 대한 고민이 필요할 것으로 생각됩니다.

## Reference
### Articles and Videos
#### 1. Clean Architecture
- 🚀[Detailed Guide on Android Clean Architecture](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011)
- [Android — Retrieving Google Location using RxJava and Clean Architecture](https://medium.com/@PedroOkawa/android-retrieving-google-location-using-rxjava-and-clean-architecture-36c1017c6529)
- [How to handle exceptions with clean architecture and Firebase](https://medium.com/firebase-tips-tricks/how-to-handle-exceptions-with-clean-architecture-and-firebase-5efbc13a1d54)
- 🚀[Android: Error handling in Clean Architecture](https://proandroiddev.com/android-error-handling-in-clean-architecture-844a7fc0dc03)
- [Android Architecture Part 5: How to Test Clean Architecture](https://five.agency/android-architecture-part-5-test-clean-architecture/)
- [Clean Architecture는 모바일 개발을 어떻게 도와주는가? - (1) 경계선: 계층 나누기](https://medium.com/@justfaceit/clean-architecture%EB%8A%94-%EB%AA%A8%EB%B0%94%EC%9D%BC-%EA%B0%9C%EB%B0%9C%EC%9D%84-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%84%EC%99%80%EC%A3%BC%EB%8A%94%EA%B0%80-1-%EA%B2%BD%EA%B3%84%EC%84%A0-%EA%B3%84%EC%B8%B5%EC%9D%84-%EC%A0%95%EC%9D%98%ED%95%B4%EC%A4%80%EB%8B%A4-b77496744616)
- 🚀[Let’s design together with Clean Architecture in Android](https://medium.com/swlh/design-together-clean-architecture-android-77624cf5d385)
- [Clean Architecture — Kotlin, Dagger 2, RxJava, MVVM and Unit Testing](https://medium.com/@rahul.singh/clean-architecture-kotlin-dagger-2-rxjava-mvvm-and-unit-testing-dc05dcdf3ce6)
- [Repository layer using Room and Dagger 2 — Android](https://android.jlelse.eu/repository-layer-using-room-and-dagger-2-android-12d311830fd9)


#### 2. MVI
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

#### 3. Hilt
- [Dependency Injection in Android with Hilt: First Impression](https://medium.com/@amritlalsahu5/dependency-injection-in-android-with-hilt-41fb915997e4)
- [Dagger - Hilt 간보기](https://two22.tistory.com/4)
- [Dagger Hilt로 안드로이드 의존성 주입 시작하기](https://hyperconnect.github.io/2020/07/28/android-dagger-hilt.html)
- [YouTube - [드로이드나이츠 2020] 옥수환 - Hilt와 함께 하는 안드로이드 의존성 주입](https://www.youtube.com/watch?v=gkUCs6YWzEY)
- [Hilt — The Future of Dependency Injection in Android](https://levelup.gitconnected.com/hilt-the-future-of-dependency-injection-in-android-e9a919c0993d)

#### 4. Git
- [좋은 Readme 작성법](https://always0ne.github.io/github/Readme/)
- 🚀[awesome-readme](https://github.com/matiassingers/awesome-readme)
- [Android Github Actions #1](https://medium.com/@lee199402/android-github-actions-1-fd4754fa6b19)
- [Github Action 을 이용한 CI 구축하기](https://dublin-java.tistory.com/65)
- [Accessing an Android app secret from GitHub Actions using Gradle](https://blog.jakelee.co.uk/accessing-android-app-secret-from-github-actions-using-gradle/)

### Github - Repository
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
