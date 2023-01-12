//package com.parade621.booksearchapp.data.api
//
//import com.parade621.booksearchapp.utils.Constants.BASE_URL
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//
///**
// * obeject와 lazy 키워드를 조합함으로서, 실제 사용되는 순간 객체가 만들어 지게되고
// * 단 하나의 인스턴스만이 만들어시도록 싱클턴 패턴으로 작성한다.
// */
//object RetrofitInstance {
//    private val okHttpClient: OkHttpClient by lazy {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    // 빌더 패턴을 통해 Retrofit 객체를 생성한다.
//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(okHttpClient) // logCat에서 packet 내용을 모니터링 하기 위함.
//            .baseUrl(BASE_URL)
//            .build()
//    }
//
//    val api: BookSearchApi by lazy {
//        retrofit.create(BookSearchApi::class.java)
//    }
//}