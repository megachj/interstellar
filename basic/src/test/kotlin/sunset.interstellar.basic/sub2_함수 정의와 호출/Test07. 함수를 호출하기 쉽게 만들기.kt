package sunset.interstellar.basic.`sub2_함수 정의와 호출`

import org.junit.jupiter.api.Test
import sunset.strings.joinToString

class Test07 {

    @Test
    fun `함수 호출 쉽게 만들기`() {
        val list = listOf(1, 2, 3)

        println(list) // 리스트의 기본 toString()
        // joinToString 은 최상위 함수
        println(joinToString(list, "; ", "(", ")")) // 함수 호출 부분 가독성이 좋지 않다
        println(joinToString(list, separator = " ", prefix = " ", postfix = "."))
        println(joinToString(list, "; "))
        println(joinToString(list, postfix = "."))
    }
}

// 최상위(클래스 바깥에 선언) 프로퍼티
var opCount = 0 // 선언
fun performOperation() {
    opCount++ // 최상위 프로퍼티 값 변경
}

fun reportOperationCount() {
    println("최상위 프로퍼티 $opCount times 오퍼레이션 실행")
}

const val UNIX_LINE_SEPARTOR = "\n" // 자바: public static final String ~
