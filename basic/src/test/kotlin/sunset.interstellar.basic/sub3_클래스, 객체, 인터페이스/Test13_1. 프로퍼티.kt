package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

import org.junit.jupiter.api.Test

class Test13_1 {

    @Test
    fun `게터 세터에서 뒷받침하는 필드 접근`() {
        val user = User5("Alice")
        user.address = "대왕판교로 47" // 내부적으로 address 의 세터를 호출한다.
    }

    @Test
    fun `비공개 세터`() {
        val lengthCounter = LengthCounter()
        // lengthCounter.counter = 0 // counter 는 비공개 세터를 가진 프로퍼티이다.
        lengthCounter.addWord("Hi!")
        println(lengthCounter.counter)
    }
}

// 코틀린에서는 인터페이스에 추상 프로퍼티 선언을 넣을 수 있다.
interface User1 {
    // User1 인터페이스 구현 클래스는 nickname 값을 얻을 수 있는 방법을 제공해야 한다.
    // 사실 인터페이스는 아무 상태도 포함할 수 없으므로 상태를 저장해야한다면 구현 클래스에서 상태 저장을 위한 프로퍼티 등을 만들어야 한다.
    val nickname: String
}

class PrivateUser(override val nickname: String) : User1 // 주 생성자에 있는 프로퍼티, 실제 프로퍼티를 만들고 거기다 저장함

class SubscribingUser(val email: String) : User1 {
    override val nickname: String
        get() = email.substringBefore('@') // 커스텀 게터, 프로퍼티를 만들지 않고 매번 이메일 주소에서 별명을 계산해서 반환
}

class FacebookUser(val accountId: Int) : User1 {
    override val nickname = getFacebookName(accountId) // 프로퍼티 초기화 식, 한번만 계산해서 필드에 저장하고, 다음부턴 필드값을 반환

    fun getFacebookName(accountId: Int): String {
        // accountId 로 name 을 조회하기
        val name = accountId.toString()

        return name
    }
}

interface User4 {
    val email: String // 추상 프로퍼티인 email, 반드시 오버라이드 해야 함
    val nickname: String // 커스텀 게터가 있는 프로퍼티
        get() = email.substringBefore('@') // 프로퍼티에 뒷받침하는 필드가 없다. 대신 매번 결과를 계산해 돌려준다.
}

class User5(val name: String) {
    var address: String = "unspecified"
        // 접근자 본문에서는 'field' 라는 특별한 식별자를 통해 뒷받침하는 필드에 접근할 수 있다.
        // 게터에서는 field 값 읽기만 가능하고, 세터에서는 읽기 쓰기 둘다 된다.
        set(value: String) {
            // 뒷받침하는 필드(address 프로퍼티에 해당하는 필드) 값 읽기
            println(
                """
                Address was changed for $name:
                "$field" -> "$value" 
                """.trimIndent()
            )
            field = value // 뒷받침하는 필드 값 변경하기
        }
}

class LengthCounter {
    // 비공개 세터가 있는 프로퍼티. 이 클래스 밖에서 이 프로퍼티의 값을 바꿀 수 없다.
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}
