package sunset.interstellar.basic.`sub1_코틀린 기초`

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test02 {

    @Test
    fun `Person 클래스 사용`() {
        val person = Person("침착맨", true)
        println("${person.name}, ${person.isMarried}")

        person.isMarried = false // 세터도 마찬가지로 코틀린이 자동으로 호출한다
    }

    @Test
    fun `Rectangle 클래스 사용`() {
        val rectangle = Rectangle(3, 4)
        println("정사각형 인가? ${rectangle.squareVal}, ${rectangle.isSquareVal}, ${rectangle.isSquareFun()}")

        Assertions.assertFalse(rectangle.squareVal)
        Assertions.assertFalse(rectangle.isSquareVal)
        Assertions.assertFalse(rectangle.isSquareFun())
    }
}

class Person(
    val name: String, // 읽기 전용 프로퍼티, 코틀린은 (비공개) 필드와 (공개) 게터를 만들어낸다.
    var isMarried: Boolean // 읽기,쓰기 프로퍼티, 코틀린은 (비공개) 필드, (공개) 게터, 세터를 만들어낸다.
)

class Rectangle(val height: Int, val width: Int) {
    val squareVal = height == width
    val isSquareVal: Boolean // 커스텀 접근자
        get() { // isSquare 프로퍼티에 게터 선언
            return height == width
        }

    fun isSquareFun() = height == width
}
