package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

import org.junit.jupiter.api.Test
import java.io.Serializable

class Test12 {

    @Test
    fun `인터페이스 활용하기`() {
        val button = Button()
        button.click()
        button.setFocus(true)
        button.showOff()
    }
}

interface Clickable {
    fun click() // 일반 메소드 선언
    fun showOff() = println("I'm clickable!") // 디폴트 구현이 있는 메소드
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

// 인터페이스는 개수 제한 없이 구현할 수 있지만, 클래스는 오직 하나만 확장할 수 있다.
class Button : Clickable, Focusable {

    // 자바와 달리 override 변경자를 꼭 사용해야 한다(상위 클래스와 시그니처가 같은 메소드는 컴파일이 안됨)
    override fun click() = println("I was clicked")
    override fun showOff() { // 이름과 시그니처가 같은 멤버 메소드에 대해 둘 이상의 디폴트 구현이 있으면 명시적으로 override 해야한다.
        super<Clickable>.showOff() // 상위 타입을 <> 안에 넣어서 'super' 를 지정하면 어떤 상위 타입 메소드를 호출할지 지정할 수 있다.
        super<Focusable>.showOff()
    }
}

// 코틀린 클래스와 메소드는 기본적으로 final 이다(상속, 오버라이드 불가능).
// open: 상속, override 를 허용한다는 변경자
open class RichButton : Clickable { // 이 클래스는 열려있다. 다른 클래스가 상속할 수 있다.
    fun disable() {} // 이 함수는 파이널이다. 하위 클래스가 이 메소드를 오버라이드할 수 없다.
    open fun animate() {} // 이 함수는 열려있다. 하위 클래스에서 이 메소드를 오버라이드해도 된다.
    override fun click() {} // 이 함수는 (상위 클래스에서 선언된) 열려있는 메소드를 오버라이드한다. 오버라이드한 메소드는 기본적으로 열려있다.
}

open class RichButton2 : Clickable {
    // final 이 없으면 override 메소드나 프로퍼티는 기본적으로 열려있다. final 을 쓰면 하위 클래스에서 이제는 오버라이드할 수 없게된다.
    final override fun click() {}
}

// 추상 클래스는 인스턴스를 만들 수 없다.
abstract class Animated {
    abstract fun animate() // 추상 함수: 구현이 없고, 하위 클래스에서 반드시 오버라이드해야 한다.
    open fun stopAnimating() {} // 추상 클래스에 속했어도 비추상 함수는 기본적으로 final 이다. 원하면 open 으로 오버라이드를 허용할 수 있다.
    fun animateTwice() {}
}

internal open class TalkativeButton : Focusable {

    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
    fun hi() = println("Hi!")
}
/*fun TalkativeButton.giveSpeech() { // 오류: public 멤버가 자신의 internal 수신 타입인 TalkativeButton 을 노출함
    yell() // 오류: yell 에 접근할 수 없음, private 멤버는 같은 클래스 안에서만 볼 수 있음
    whisper() // 오류: whisper 에 접근할 수 없음, protected 멤버는 하위 클래스 안에서만 볼 수 있음
}*/
