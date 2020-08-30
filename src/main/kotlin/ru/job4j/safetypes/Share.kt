package ru.job4j.safetypes

import java.util.*

class Share(val name: String, val currency: String, val date: Date) {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Share) {
            return false
        }
        if (other === this) {
            return true
        }
        return other.name == name &&
                other.currency == currency && other.date == date
    }

    override fun hashCode(): Int {
        var result = 17
        result = 31 * result + name.hashCode()
        result = 31 * result + currency.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}
