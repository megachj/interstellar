package sunset.interstellar.basic.`sub1_코틀린 기초`

fun 고전iteration() {
    var i = 10
    while (i > 0) {
        println("descending loop: $i")
        i--
    }

    i = 10
    do {
        println("descending loop: $i")
        i--
    } while(i > 0)
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun fizzBuzz실행() {
    println("FizzBuzz 1..100")
    for (i in 1..100) { // 코틀린은 1 <= i <= 100 인 폐구간
        print(fizzBuzz(i) + " ")
    }

    // in 100: 100 부터
    // downTo 1: -1씩 내려가며
    // step 2: 절대값을 2로 즉, -2씩 내려감
    println("\n\nFizzBuzz 100, 98, 96, ..., 2")
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i) + " ")
    }
}
