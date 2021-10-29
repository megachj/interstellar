package sunset.strings

// 최상위(클래스 바깥에 선언) 함수
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ", // 디폴트 파라미터 값
    prefix: String = "",
    postfix: String = "",
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

/*
아래 확장 함수에서 사용된 의미는 다음과 같다.
- 수신 객체 타입, String: 해당 함수가 확장되어 정의될 클래스 타입
- 수신 객체, this: 그 클래스에 속한 인스턴스 객체
 */
fun String.lastChar(): Char = this.get(this.length - 1)
fun String.lastChar2(): Char = get(length - 1) // 일반 메소드와 마찬가지로 확장 함수 본문에서도 this 를 생략할 수 있다

// joinToString 을 확장 함수로 정의하기
fun <T> Collection<T>.joinToStringExpansion(
    separator: String = ", ", // 디폴트 파라미터 값
    prefix: String = "(",
    postfix: String = ")",
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 확장 프로퍼티
// 프로퍼티 이름으로 불리기는 하지만 상태를 저장할 적절한 방법이 없기 때문에(기존 클래스 인스턴스 객체에 필드를 추가할 방법은 없다) 실제 확장 프로퍼티는 아무 상태도 가질 수 없다.
val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1) // 프로퍼티 게터
    set(value: Char) { // 프로퍼티 세터
        setCharAt(length - 1, value)
    }
