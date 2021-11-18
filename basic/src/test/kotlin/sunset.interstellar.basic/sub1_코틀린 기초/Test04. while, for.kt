package sunset.interstellar.basic.`sub1_코틀린 기초`

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.TreeMap

class Test04 {

    @Test
    fun `while, do~while 루프`() {
        var i = 10
        while (i > 0) {
            println("descending loop: $i")
            i--
        }

        var j = 10
        do {
            println("descending loop: $j")
            j--
        } while (j > 0)
    }

    @Test
    fun `for 루프`() {
        println("FizzBuzz 1..100")
        for (i in 1..100) { // 코틀린은 1 <= i <= 100 인 폐구간
            print(fizzBuzz(i) + " ")
        }

        println("\n\nFizzBuzz 1..100")
        for (i in 1 until 100 + 1) { // for (i in 1..100) 과 같음
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

    @Test
    fun `맵 이터레이션`() {
        val binaryReps = TreeMap<Char, String>() // 키에 대해 정렬하기 위해 TreeMap 사용
        for (c in 'A'..'F') { // A 부터 F 까지 문자의 범위를 사용해 이터레이션한다.
            val binary = Integer.toBinaryString(c.code) // 아스키 코드를 2진 표현으로 바꾼다
            binaryReps[c] = binary // c 를 키로 c 의 2진 표현을 맵에 넣는다.
        }
        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")
        }

        // 인덱스와 함께 컬렉션 이터레이션
        val list = arrayListOf("10", "11", "1001")
        for ((index, element) in list.withIndex()) {
            println("$index: $element")
        }
    }

    @Test
    fun `in으로 컬렉션이나 범위의 원소 검사`() {
        // in 을 사용해 값이 범위에 속하는지 검사하기
        fun isLetter(c: Char) =
            c in 'a'..'z' || c in 'A'..'Z' // ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') 으로 변환

        fun isNotDigit(c: Char) = c !in '0'..'9'

        Assertions.assertTrue(isLetter('q'))
        Assertions.assertTrue(isNotDigit('!'))

        // when 에서 in 사용하기
        fun recognize(c: Char) = when (c) {
            in '0'..'9' -> "It's a digit!"
            in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
            else -> "I don't know"
        }
        println(recognize('8'))

        // Comparable 인터페이스 구현한 클래스도 in 범위 안에 속하는지 검사할 수 있다.
        Assertions.assertTrue("Kotlin" in "Java".."Scala") // "Java" <= "Kotlin" && "Kotlin" <= "Scala" 와 같다
        Assertions.assertFalse("Kotlin" in setOf("Java", "Scala"))
    }
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}
