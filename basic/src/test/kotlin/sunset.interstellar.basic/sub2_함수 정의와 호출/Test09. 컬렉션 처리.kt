package sunset.interstellar.basic.`sub2_함수 정의와 호출`

import org.junit.jupiter.api.Test

class Test09 {

    @Test
    fun `가변 인자 함수`() {
        println(createStringList("침착맨", "주펄", "김풍"))
    }

    @Test
    fun `중위 호출과 구조 분해 선언`() {
        // 중위 호출은 인자가 하나뿐인 일반 메소드나, 확장 함수에만 사용할 수 있다.
        val map = mapOf<Int, String>(
            1.to("one"), // "to" 메소드를 일반 방식으로 호출
            7 to "seven", // "to" 메소드를 중위 호출 방식으로 호출
            10 infixTo "ten",
            12.notInfixTo("twelve"),
        )

        // 아래처럼 Pair 내용으로 두 변수를 즉시 초기화할 수 있는데, 이런 기능을 '구조 분해 선언' 이라고 부른다.
        val (number, name) = 1 to "one"
        for ((index, element) in listOf("침착맨", "주펄", "김풍").withIndex()) {
            println("$index: $element")
        }
    }
}

// vararg 를 사용해 가변 인자를 정의할 수 있다.
fun createStringList(vararg values: String): List<String> {
    return listOf(*values) // * 연산자(스프레드 연산자) 를 이용
}

// 함수를 중위 호출에 사용하게 허용하고 싶으면 infix 변경자를 함수 선언 앞에 추가해야 한다.
// 당연히 infix 함수의 인자는 하나뿐이어야 한다.
infix fun Int.infixTo(other: String) = Pair(this, other)
fun Int.notInfixTo(other: String) = Pair(this, other)
