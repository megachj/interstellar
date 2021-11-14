package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

class Test12_2

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        // 디폴트 분기, 코틀린 컴파일러는 when 을 사용해 Expr 타입의 값을 검사할 때 꼭 디폴트 분기인 else 분기를 강제한다.
        else ->
            throw IllegalArgumentException("Unknown expression")
    }

// 기반 클래스를 sealed 로 봉인한다.
// sealed 클래스는 자동으로 open 이다.
sealed class SealedExpr {

    // 기반 클래스의 모든 하위 클래스를 중첩 클래스로 나열한다.
    class Num(val value: Int) : SealedExpr()
    class Sum(val left: Expr, val right: Expr) : SealedExpr()
}

fun eval(e: SealedExpr): Int =
    // when 식이 모든 하위 클래스를 검사하므로 별도의 else 디폴트 분기가 없어도 된다.
    when (e) {
        is SealedExpr.Num -> e.value
        is SealedExpr.Sum -> eval(e.left) + eval(e.right)
    }
