package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test14 {

    @Test
    fun `클래스와 데이터 클래스`() {
        val client1 = Client("침착맨", 1)
        val client2 = Client("침착맨", 1)
        val setClient = hashSetOf(client1, client2)
        Assertions.assertEquals(client1, client2)
        Assertions.assertTrue(setClient.size == 1)
        println(client1)

        val dataClient1 = DataClient("주펄", 2)
        val dataClient2 = DataClient("주펄", 2)
        val setDataClient = hashSetOf(dataClient1, dataClient2)
        Assertions.assertEquals(dataClient1, dataClient2)
        Assertions.assertTrue(setDataClient.size == 1)
        println(dataClient1)

        // data class 는 copy 메소드도 정의되어 있음
        val homin = DataClient("호민", 2)
        println(homin.copy("호민", 3))
    }
}

class Client(val name: String, val postalCode: Int) {
    // 객체 동등 비교 == 는 내부적으로 equals 를 호출한다. 그리고 코틀린에서 객체 참조 비교는 === 이다.
    override fun equals(other: Any?): Boolean { // Any 는 코틀린의 모든 클래스의 최상위 클래스다. Any? 는 널이 될 수 있는 타입이다.
        if (other == null || other !is Client) // other 가 Client 인지 검사한다.
            return false
        return name == other.name &&
                postalCode == other.postalCode
    }

    // HashMap, HashSet 과 같은 해시 기반 컨테이너에서 키로 사용할 수 있는 hashCode() 이다.
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode

    override fun toString() = "Client(name=$name, postalCode=$postalCode)"
}

// 데이터 클래스: 모든 클래스가 정의해야 하는 메소드 자동 생성(toString, equals, hashCode), copy 메소드도 생성
data class DataClient(val name: String, val postalCode: Int)
