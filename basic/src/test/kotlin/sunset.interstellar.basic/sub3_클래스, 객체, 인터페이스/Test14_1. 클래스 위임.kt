package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

import org.junit.jupiter.api.Test

class Test14_1 {

    @Test
    fun `클래스 위임`() {
        val cset = CountingSet<Int>()
        cset.addAll(listOf(1, 1, 2))
        println("${cset.objectsAdded} objects were added, ${cset.size} remain")
    }
}

// 데코레이터 패턴을 이용해 위임 클래스 만들기
class DelegatingCollection<T> : Collection<T> {
    private val innerList = arrayListOf<T>()

    override val size: Int get() = innerList.size

    override fun contains(element: T): Boolean = innerList.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)

    override fun isEmpty(): Boolean = innerList.isEmpty()

    override fun iterator(): Iterator<T> = innerList.iterator()
}

// 코틀린 언어는 위임 기능을 by 키워드를 이용해 제공한다.
class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet { // MutableCollection 의 구현을 innerSet 에게 위임한다.

    var objectsAdded = 0
    override fun add(element: T): Boolean { // 이 메소드는 위임하지 않고 새로운 구현을 제공
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean { // 이 메소드는 위임하지 않고 새로운 구현을 제공
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}
