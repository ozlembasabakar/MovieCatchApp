package com.example.moviecatch.util

class StringHelper {

    fun getTrItem(name: String): String {

        var tr_name = ""
        tr_name = when (name) {
            "Action" -> "Aksiyon"
            "Adventure" -> "Macera"
            "Animation" -> "Animasyon"
            "Comedy" -> "Komedi"
            "Crime" -> "Suç"
            "Documentary" -> "Belgesel"
            "Drama" -> "Dram"
            "Family" -> "Aile"
            "Fantasy" -> "Fantazi"
            "History" -> "Tarih"
            "Horror" -> "Korku"
            "Music" -> "Müzik"
            "Mystery" -> "Gizem"
            "Romance" -> "Romantik"
            "Science Fiction" -> "Bilim Kurgu"
            "TV Movie" -> "TV Filmi"
            "Thriller" -> "Gerilim"
            "War" -> "Savaş"
            "Western" -> "Kovboy"
            else -> ""
        }
        return tr_name
    }
}