package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

import org.junit.jupiter.api.Test
import sunset.interstellar.basic.`sub1_코틀린 기초`.Person

class Test15 {

    @Test
    fun `객체 선언, 싱글턴 쉽게 만들기`() {
        Payroll.allEmployees.add(Person("침착맨", true))
        Payroll.caculateSalary()
    }

    @Test
    fun `동반 객체`() {
        A.bar()

        val subscribingUser = User6.newSubscribingUser("bob@gmail.com")
        val facebookUser = User6.newFacebookUser(4)
        println(subscribingUser.nickname)
    }
}

// 객체 선언: 싱글턴 객체
object Payroll {
    val allEmployees = arrayListOf<Person>()
    fun caculateSalary() {
        // ...
    }
}

// 클래스 안에서 객체를 선언할 수도 있는데, 이 중첩 객체도 인스턴스는 단 하나뿐이다(바깥 클래스의 인스턴스마다 중첩 객체의 인스턴스가 하나씩 생기는 것이 아니다.)
data class Person1(val name: String) {

    // 중첩 객체
    object NameComparator : Comparator<Person1> {
        override fun compare(p1: Person1, p2: Person1): Int = p1.name.compareTo(p2.name)
    }
}

class A {
    // A 클래스의 동반 객체. 자바의 정적 메소드 호출, 정적 필드 사용 구문과 같다
    // 동반 객체는 자신을 둘러싼 클래스의 모든 private 멤버에 접근할 수 있다.
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

// 부 생성자를 팩토리 메소드로 대신하기
class User6 private constructor(val nickname: String) { // 주 생성자를 비공개로 만든다
    companion object { // 동반 객체를 이용해 팩토리 메소드 정의
        fun newSubscribingUser(email: String) =
            User6(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) =
            User6(getFacebookName(accountId))
    }
}

fun getFacebookName(accountId: Int): String {
    // DB 조회...
    return accountId.toString()
}

// 동반 객체에 이름 붙이기
class PersonA(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): PersonA {
            return PersonA("JSON")
        }
    }
}

// 동반 객체에서 인터페이스 구현하기
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}
