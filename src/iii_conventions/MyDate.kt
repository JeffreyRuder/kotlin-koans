package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (this.year == other.year && this.month == other.month && this.dayOfMonth == other.dayOfMonth) {
            return 0
        } else if (this.year < other.year) {
            return -1
        } else if (this.year > other.year) {
            return 1
        } else {
            if (this.month < other.month) {
                return -1
            } else if (this.month > other.month) {
                return 1
            } else {
                if (this.dayOfMonth < other.dayOfMonth) {
                    return -1
                } else {
                    return 1
                }
            }
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate = this.addTimeIntervals(repeatedTimeInterval.ti, repeatedTimeInterval.n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return DateIterator(this)
    }
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var date: MyDate = dateRange.start

    override fun hasNext(): Boolean {
        return date <= dateRange.endInclusive
    }

    override fun next(): MyDate {
        val result = date
        date = date.nextDay()
        return result
    }

}

