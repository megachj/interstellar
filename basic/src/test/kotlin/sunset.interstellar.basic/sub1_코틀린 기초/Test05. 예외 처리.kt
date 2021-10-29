package sunset.interstellar.basic.`sub1_코틀린 기초`

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.StringReader

class Test05 {

    @Test
    fun `예외에 대한 내용`() {
        val percentage = 200
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            if (percentage !in 0..100) {
                throw IllegalArgumentException("퍼센트는 0~100 까지이다. $percentage")
            }
        }

        val number = 200
        try {
            // 변수 선언시에 if ~ else 를 사용할 수 있고, 예외를 발생하게 만들 수 있다
            val percentage2 =
                if (number in 0..100)
                    number
                else
                    throw IllegalArgumentException("퍼센트는 0~100 까지이다. $number") // 예외 발생
        } catch (e: Exception) {
            println("percentage2 변수생성중 에러 발생")
        }

        // 자바와 마찬가지인 try-catch-finally
        val reader1 = BufferedReader(StringReader("239"))
        println(readNumber1(reader1))

        val reader2 = BufferedReader(StringReader("not a number"))
        readNumber2(reader2) // 아무것도 출력되지 않는다
        readNumber3(reader2)
    }
}

// 자바와 마찬가지인 try-catch-finally
fun readNumber1(reader: BufferedReader): Int? { // 함수가 던질 수 있는 예외를 명시할 필요가 없다
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return // null 이 아님 그냥 없는 값인듯
    }
    println(number)
}

fun readNumber3(reader: BufferedReader) {
    // 중괄호에선 마지막 값이 리턴값이므로 생략 가능
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null // 예외 발생시 null 값 사용
    }
    println(number)
}
