# Book Search Application

### 카카오 책 검색 API를 사용해 책을 검색하는 어플리케이션
<br/>

Modern Android Architecture를 스터디하며 만들어본 Sample Code

이후로도 안드로이드 스터디를 진행하며 지속적으로 리팩토링이 이루어질 예정입니다.
<br/>
<br/>
추가적으로 제가 실무에서 사용할 가능성이 높아보이는 라이브러리와 기능 등을 추가하여 이후에 쉽게 가져다 쓸 수 있는 레퍼런스로 사용하기 위해 샘플 프로젝트로 확장 중 입니다.


Jetpack Compose는 별도의 BookSearchAppWithCompose를 작업 중에 있습니다.

<br/><br/>
  
# 🔎 주요 기능

<div style="display: flex; justify-content: space-around; align-items: flex-start;">
  <figure>
    <img src="https://github.com/parade621/BookSearchApp/blob/main/photo/BookSearchApp_Search.gif" width="20%" />
    <figcaption>책 검색 기능</figcaption>
  </figure>
  <figure>
    <img src="https://github.com/parade621/BookSearchApp/blob/main/photo/2023-01-04_151318.gif" width="22%" />
    <figcaption>정보 제공 기능</figcaption>
  </figure>
  <figure>
    <img src="https://github.com/parade621/BookSearchApp/blob/main/photo/2023-01-04_200028.gif" width="22%" />
    <figcaption>관심 목록 저장 및 기능</figcaption>
  </figure>
</div>

<br/><br/>


## 🛠️ 사용 기술 및 라이브러리

---
<br/>

- MVVM Pattern
- Android Jectpack
    - ViewModel
    - ViewBinding
    - LiveData
    - Navigation
    - ConstraintLayout
- Retrofit2 + okHttp3
- Kotlin Coroutine
- Kakao open API

## 💡 성장한 부분

---

- MVVM 패턴을 정확히 적용하는 방법을 학습할 수 있었습니다.
- 다양한 jetpack library를 학습하고 직접 적용해며 그 동작과 원리를 학습하였습니다.
- Retrofit을 사용해 서버로 Data를 요청하여 원하는 작업을 수행하는 방법을 학습 할 수 있었습니다.
