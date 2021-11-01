package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

import org.junit.jupiter.api.Test
import sunset.interstellar.basic.`sub1_코틀린 기초`.Person

class Test15 {

    @Test
    fun `객체 선언, 싱글턴 쉽게 만들기`() {
        Payroll.allEmployees.add(Person("침착맨", true))
        Payroll.caculateSalary()
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
