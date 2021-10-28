package sunset.interstellar.basic.`sub1_코틀린 기초`

// fun: 함수
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b
fun max3(a: Int, b: Int) = if (a > b) a else b

// val: immutable 변수, 선언시 무조건 초기화 필요
val 침착맨 = "이병건"
val answer = 1
val answer2: Int = 2 // 원하면 타입 쓰기 가능
val yearsToCompute = 7.5e6 // 부동소수점 상수를 사용하면 타입은 Double 이 된다.

fun val특성알아보기() {
    // val 참조가 가리키는 객체 내부 값은 변경이 된다
    val languages = arrayListOf("Java")
    languages.add("Kotlin")
}

// var: mutable 변수
fun var특성알아보기() {
    var number = 1
    println("before: ${number}")

    // number = "일" // 변수의 타입은 고정된다

    number = 2
    println("after: ${number}")
}

// 문자열 템플릿
fun hello(name: String, args: ArrayList<String>) {
    println("Hello ${name}! your args 2.. ${args[0]}, ${args[1]}")
}
