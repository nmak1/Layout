package ru.netology.layout.Until

import java.math.RoundingMode


import java.text.DecimalFormat

object ConvertNumber {

    private const val THOUSAND = 1_000
    private const val MILLION = 1_000_000


    fun counterDecimal(count: Long): String {
        val value = count.toDouble()
        val formatter = DecimalFormat("##.#")
        formatter.roundingMode = RoundingMode.DOWN
        return if (value < 1100.0) {
            formatter.format(value)
        } else {
            if (count in 1100..10000) {
                formatter.format(value / THOUSAND)+"K"
            } else {
                if (count in 10001 until MILLION) {
                    val formate = DecimalFormat("#")
                    formate.format(value / THOUSAND) + "K"
                } else formatter.format(value / MILLION) + "M"

            }
        }
    }
}
