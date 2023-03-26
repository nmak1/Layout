package ru.netology.layout.Until

import java.math.RoundingMode


import java.text.DecimalFormat

object ConvertNumber {

    private const val ZERO = 0
    private const val THOUSAND = 1_000
    private const val MILLION = 1_000_000

    private fun letterToCount(value: Long): String {
        return if (value in THOUSAND until MILLION) {
            "K"
        } else if (value >= MILLION) {
            "M"
        } else {
            ""
        }
    }

    private fun counter(value: Long): String {
        val result = when (value) {
            in ZERO until THOUSAND -> value
            in THOUSAND until MILLION -> value / THOUSAND
            in MILLION..Long.MAX_VALUE -> value / MILLION
            else -> ZERO
        }
        return result.toString()
    }

    private fun roundingToDecimal(value: Float): String {

        if (value in THOUSAND.toFloat()..(MILLION.toFloat() - 1f) && value % THOUSAND.toFloat() != ZERO.toFloat()&&value>= 10*THOUSAND.toFloat()){
            val result = DecimalFormat("#")
            result.roundingMode = RoundingMode.DOWN
            return result.format(value)
        } else{
            val result = DecimalFormat("#.#")
            result.roundingMode = RoundingMode.DOWN
            return result.format(value)
        }

    }

    fun counterDecimal(value: Long): String {
        val valueToFloat = value.toFloat()
        val valueDivThousand = valueToFloat / THOUSAND.toFloat()
        val valueDivMillion = valueToFloat / MILLION.toFloat()

        val result =
            if (valueToFloat in THOUSAND.toFloat()..(MILLION.toFloat() - 1f) && valueToFloat % THOUSAND.toFloat() != ZERO.toFloat()) {
                roundingToDecimal(valueDivThousand)
            }  else if (valueToFloat >= MILLION.toFloat()) {
                roundingToDecimal(valueDivMillion)
            } else counter(value)

        return result + letterToCount(value)
    }
}