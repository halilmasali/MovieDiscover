package com.halilmasali.moviediscover

object Constants {
    const val ApiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNjAyZTg0YWI2ZDg2ZmIwOG" +
            "ZhNjVmNzBkMzA1YWVhYyIsInN1YiI6IjY0OTJiZjlmZjlhYTQ3MDEwNjBlYWZiNSIsInNjb3B" +
            "lcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ygWygBuiAWTaxFQrYBxAMdGfqq3KEPq43gDl4U89Jj4"
    const val ApiBaseUrl = "https://api.themoviedb.org/3/"
    const val ImageBaseUrl = "https://image.tmdb.org/t/p/w500/"
    const val ExpirationTime:Long = 600000 //10 dk
    // Room Database Constants
    const val SeriesAiringToday = "SeriesAiringToday"
    const val SeriesOnTheAir = "SeriesOnTheAir"
    const val SeriesPopular = "SeriesPopular"
    const val SeriesTopRated = "SeriesTopRated"
    const val MovieNowPlaying = "MovieNowPlaying"
    const val MoviePopular = "MoviePopular"
    const val MovieTopRated = "MovieTopRated"
    const val MovieUpcoming = "MovieUpcoming"
}