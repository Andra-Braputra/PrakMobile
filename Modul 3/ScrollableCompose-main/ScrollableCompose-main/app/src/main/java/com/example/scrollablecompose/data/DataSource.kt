package com.example.scrollablecompose.data


import com.example.scrollablecompose.R
import com.example.scrollablecompose.model.Fallout

class Datasource {
    fun loadFallout(): List<Fallout> {
        return listOf(
            Fallout(1, R.string.judul1, R.string.deskripsi1, R.drawable.img1, R.string.wiki1, R.string.year1, R.string.detail1),
            Fallout(2, R.string.judul2, R.string.deskripsi2, R.drawable.img2, R.string.wiki2, R.string.year2, R.string.detail2),
            Fallout(3, R.string.judul3, R.string.deskripsi3, R.drawable.img3, R.string.wiki3, R.string.year3, R.string.detail3),
            Fallout(4, R.string.judul4, R.string.deskripsi4, R.drawable.img4, R.string.wiki4, R.string.year4, R.string.detail4),
            Fallout(5, R.string.judul5, R.string.deskripsi5, R.drawable.imgnv, R.string.wikinv, R.string.year5, R.string.detail5)
        )
    }
}