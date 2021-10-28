package sunset.interstellar.basic.`sub1_코틀린 기초`

class Person(
    val name: String, // 읽기 전용 프로퍼티, 코틀린은 (비공개) 필드와 (공개) 게터를 만들어낸다.
    var isMarried: Boolean // 쓸 수 있는 프로퍼티, 코틀린은 (비공개) 필드, (공개) 게터, 세터를 만들어낸다.
)

fun Person클래스사용() {
    val person = Person("침착맨", true)
    println("${person.name}, ${person.isMarried}") // 프로퍼티 이름을 직접 사용해도 코틀린이 자동으로 게터를 호출해준다.

    person.isMarried = false // 세터도 마찬가지로 코틀린이 자동으로 호출해준다.
}

// 커스텀 접근자
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() { // isSquare 프로퍼티에 게터 선언
            return height == width
        }
}
