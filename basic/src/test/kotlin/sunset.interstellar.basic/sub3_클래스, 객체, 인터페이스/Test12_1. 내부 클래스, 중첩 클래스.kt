package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

import java.io.Serializable

class Test12_1

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class Button2 : View {
    override fun getCurrentState(): State = ButtonState2()

    override fun restoreState(state: State) {}

    // 중첩 클래스: 바깥쪽 클래스에 대한 참조를 저장하지 않음
    // 자바의 public static class ButtonState implements State 와 같다.
    class ButtonState2 : State
}

class Outer {

    // 내부 클래스: 바깥쪽 클래스에 대한 참조를 저장함
    // 자바의 public class Inner 와 같다.
    inner class Inner {
        val innerValue = "Inner 의 값"

        fun getOuterReference(): Outer = this@Outer // 바깥 클래스(Outer)에 대한 참조
    }
}
