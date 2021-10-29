package sunset.interstellar.basic.`sub1_코틀린 기초`

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test01 {

    @Test
    fun `max함수 호출해보기`() {
        Assertions.assertEquals(max1(100, 10), 100)
        Assertions.assertEquals(max2(100, 10), 100)
        Assertions.assertEquals(max3(100, 10), 100)
    }

    @Test
    fun `val은 immutable`() {
        val 침착맨 = "이병건"
        val answer = 1
        val answer2: Int = 2 // 원하면 타입 쓰기 가능
        val yearsToCompute = 7.5e6 // 부동소수점 상수를 사용하면 타입은 Double

        val languages = arrayListOf("Java")
        // languages = arrayListOf("C++") // immutable 이라 불가능
        languages.add("Kotlin") // val 참조가 가리키는 객체 내부 값은 변경이 된다
    }

    @Test
    fun `var은 mutable`() {
        var number = 1
        println("before: $number")

        // number = "일" // 변수의 타입은 고정이므로 불가능

        number = 2
        println("after: ${number}")
    }

    @Test
    fun `문자열 템플릿 확인`() {
        printHello("침착맨", listOf("주펄", "김풍"))
    }
}

// fun: 함수 정의
fun max1(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b
fun max3(a: Int, b: Int) = if (a > b) a else b

// 문자열 템플릿
fun printHello(name: String, args: List<String>) {
    println("Hello ${name}! your args 2.. ${args[0]}, ${args[1]}")
}
