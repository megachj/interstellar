package sunset.interstellar.basic.`sub2_함수 정의와 호출`

import org.junit.jupiter.api.Test
import sunset.strings.joinToStringExpansion
import sunset.strings.lastChar
import sunset.strings.lastChar2 as last // as 키워드를 사용하면 다른 이름으로 부를 수 있다

class Test08 {

    @Test
    fun `확장 함수 이용`() {
        // String 확장 함수 이용
        println("Kotlin".lastChar())
        println("Kotlin".last())

        // Collection<T> 확장 함수 이용
        val list = arrayListOf(1, 2, 3)
        println(list.joinToStringExpansion("; "))
    }

    @Test
    fun `확장 함수는 오버라이드할 수 없다`() {
        val view: View = Button() // Button 은 View 이므로 View 는 Button 을 담을 수 있다
        view.click()
        view.showOff()
    }

    @Test
    fun `확장 프로퍼티`() {
        println("Kotlin".lastChar)

        val sb = StringBuilder("Kotlin?")
        sb.lastChar = '!'
        println(sb)
    }
}

open class View {
    open fun click() = println("View clicked")
}

fun View.showOff() = println("I'm a view!") // View 타입의 확장 함수

class Button : View() {
    // 일반 함수는 오버라이드 할 수 있다
    override fun click() = println("Button clicked")
}

fun Button.showOff() = println("I'm a button!") // Button 타입의 확장 함수(오버라이드 되는 것이 아님)
