package Ödevler

fun dereceToFahrenheit(celsius: Double): Double {
    return celsius * 1.8 + 32
}

fun calculateRectanglePerimeter(shortSide:Int, longSide:Int):Int {
    return 2 * (shortSide + longSide)
}

fun factorial(number:Int): Long {
    var result:Long = 1L
    for (i in 1.. number) {
        result *= i
    }
    return result
}

fun numOfa(word:String):Int {
    return word.count { ch -> ch == 'a' || ch == 'A' }
}

fun calculateSumOfInteriorAngles(edgeNumber:Int):Int {
    return (edgeNumber - 2) * 180
}

fun calculateSalary(days: Int): Int {
    val hourlyWage = 10
    val overtimeHourlyWage = 20
    val dailyWorkingHours = 8
    val normalHourLimit = 160

    val totalHours = days * dailyWorkingHours

    return if (totalHours <= normalHourLimit) {
        totalHours * hourlyWage
    } else {
        val overtimeHours = totalHours - normalHourLimit
        (normalHourLimit * hourlyWage) + (overtimeHours * overtimeHourlyWage)
    }
}

fun calculateQuotaFee(quotaGB: Int): Int {
    val standardQuota = 50
    val standardFee = 100
    val excessFeePerGB = 4

    return if (quotaGB <= standardQuota) {
        standardFee
    } else {
        standardFee + (quotaGB - standardQuota) * excessFeePerGB
    }
}