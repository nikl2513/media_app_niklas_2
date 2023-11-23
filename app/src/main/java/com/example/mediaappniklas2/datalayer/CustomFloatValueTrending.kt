package com.example.mediaappniklas2.datalayer

class CustomFloatValueTrending private constructor(private val value: Float) {

    init {
        require(value in 1.0..10.0)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        fun create(value: Float): CustomFloatValueTrending{
            return CustomFloatValueTrending(value)
        }
    }
    
}