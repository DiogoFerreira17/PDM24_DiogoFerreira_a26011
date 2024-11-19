object RetrofitInstance{
    val api: NewsApi by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Article)
    }
}