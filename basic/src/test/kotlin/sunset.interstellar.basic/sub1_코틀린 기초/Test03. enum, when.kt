package sunset.interstellar.basic.`sub1_코틀린 기초`

import org.junit.jupiter.api.Test

class Test03 {

    @Test
    fun `enum 클래스 사용`() {
        println("빨간색 rgb: ${Color.RED.rgb()}")
        println("노란색의 mnemoni: ${getMnemonic(Color.YELLOW)}")
        println("파란색의 warmth: ${getWarmth(Color.BLUE)}")
        println("파란색, 보라색을 섞으면: ${mix(Color.BLUE, Color.VIOLET)}")
        println("노란색, 빨간색을 섞으면: ${mixOptimized(Color.YELLOW, Color.RED)}")
    }

    @Test
    fun `스마트 캐스트 사용`() {
        val expr = Sum(Sum(Num(1), Num(2)), Num(4))
        println("eval: 1+2+4 = ${eval(expr)}")
        println("evalRefactored: 1+2+4 = ${evalRefactored(expr)}")
        println("evalWithLogging")
        println("1+2+4 = ${evalWithLogging(expr)}")
    }
}

// enum 클래스 정의
enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238),
    ;

    fun rgb() = (r * 256 + g) * 256 + b
}

// when 문
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

fun getWarmth(color: Color) =
    when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
        Color.GREEN -> "neutral"
        Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
    }

// 함수가 호출될 때마다 주어진 두 색이 when 조건의 두 색과 같은지 비교하기 위해 여러 Set 인스턴스를 생성한다. 그래서 가비지 객체가 늘어난다.
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty color")
    }

fun mixOptimized(c1: Color, c2: Color) =
    // 인자 없는 when 사용
    when {
        (c1 == Color.RED && c2 == Color.YELLOW) ||
                (c1 == Color.YELLOW && c2 == Color.RED) ->
            Color.ORANGE
        (c1 == Color.YELLOW && c2 == Color.BLUE) ||
                (c1 == Color.BLUE && c2 == Color.YELLOW) ->
            Color.GREEN
        (c1 == Color.BLUE && c2 == Color.VIOLET) ||
                (c1 == Color.VIOLET && c2 == Color.BLUE) ->
            Color.INDIGO
        else -> throw Exception("Dirty color")
    }


// 스마트 캐스트: 타입 검사와 타입 캐스트(변환)를 조합
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num // Num 타입으로 변환하는데, 불필요함. 위에서 is 로 검사했기 때문에 컴파일러가 변환해줌.
        return n.value
    }
    if (e is Sum) { // 변수 e 에 대해 스마트 캐스트를 사용
        // 코틀린에서는 어떤 변수가 원하는 타입인지 일단 is로 검사하고 나면 굳이 해당 타입으로 캐스팅하지 않아도 컴파일러가 해준다.
        return eval(e.right) + eval(e.left)
    }
    throw IllegalArgumentException("Unknown expression")
}

fun evalRefactored(e: Expr): Int =
    when (e) {
        is Num ->
            e.value
        is Sum ->
            evalRefactored(e.right) + evalRefactored(e.left)
        else ->
            throw IllegalArgumentException("Unknown expression")
    }

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value // '블록의 마지막 식이 블록의 결과' 라는 규칙은 블록이 값을 만들어내야 하는 경우 항상 성립!
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }
