package sunset.interstellar.basic.`sub2_함수 정의와 호출`

import org.junit.jupiter.api.Test

class Test06 {

    @Test
    fun `컬렉션 만들기`() {
        val hashSet = hashSetOf(1, 7, 53)
        val arrayList = arrayListOf(1, 7, 53)
        val hashMap = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three") // to 는 언어가 제공하는 키워드가 아닌 일반 함수

        // 코틀린은 자체 컬렉션을 제공하지 않고 표준 자바 컬렉션을 사용한다.
        println("class: ${hashSet::class}, class.java: ${hashSet::class.java}, javaClass: ${hashSet.javaClass}")
        println("class: ${arrayList::class}, class.java: ${arrayList::class.java}, javaClass: ${arrayList.javaClass}")
        println("class: ${hashMap::class}, class.java: ${hashMap::class.java}, javaClass: ${hashMap.javaClass}")
    }
}
