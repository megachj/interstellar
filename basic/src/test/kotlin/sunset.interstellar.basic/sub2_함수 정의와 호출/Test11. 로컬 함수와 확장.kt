package sunset.interstellar.basic.`sub2_함수 정의와 호출`

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test11 {

    @Test
    fun `로컬 함수 사용`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            saveUser(User(1, "", ""))
        }
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            saveUser2(User(2, "침착맨", ""))
        }
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            saveUser3(User(3, "", "곡성"))
        }
    }
}

class User(val id: Int, val name: String, val address: String)

// 코드 중복을 보여주는 예제
fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("${user.id} empty name!")
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException("${user.id} empty address!")
    }
    // user 를 DB 에 저장한다...
}

// 로컬 함수를 사용해 코드 중복 줄이기
fun saveUser2(user: User) {
    // 로컬 함수는 자신이 속한 바깥 함수의 '모든 파라미터', '모든 변수' 를 사용할 수 있다.
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("${user.id} empty $fieldName!")
        }
    }

    validate(user.name, "name")
    validate(user.address, "address")
    // user 를 DB 에 저장한다...
}

// 검증 로직을 확장 함수로 추출하기
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("$id empty $fieldName!")
        }
    }

    validate(name, "name")
    validate(address, "address")
}

fun saveUser3(user: User) {
    user.validateBeforeSave()
    // user 를 DB 에 저장한다...
}
