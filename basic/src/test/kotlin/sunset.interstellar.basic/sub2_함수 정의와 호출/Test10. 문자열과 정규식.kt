package sunset.interstellar.basic.`sub2_함수 정의와 호출`

import org.junit.jupiter.api.Test

class Test10 {

    @Test
    fun `문자열 나누기`() {
        // 코틀린 정규식 문법은 자바와 동일하다.
        val `마침표나 대시로 문자열 분리 Regex`: Regex = "\\.|-".toRegex()
        println("12.345-6.A".split(`마침표나 대시로 문자열 분리 Regex`))
        println("12.345-6.A".split(".", "-")) // 복잡하지 않은건 구분 문자열을 지정해주는게 훨씬 편함

        val path = "/Users/yole/kotlin-book/chapter.adoc"
        parsePath(path)
        parsePath2(path)
    }

    @Test
    fun `3중 따옴표 문자열`() {
        // 여러 줄 문자열에는 들여쓰기, 줄 바꿈을 포함한 모든 문자가 들어간다
        val kotlinLogo = """|  //
            .| //
            .|/ \"""

        println(kotlinLogo.trimMargin("."))

        val path = "C:\\Users\\yole\\kotlin-book" // 한줄 문자열에서는 \ 쓰려면 이스케이프해야해서 \\ 를 써야한다.
        val path2 = """C:\Users\yole\kotlin-book""" // 여러줄 문자열에서는 \ 를 이스케이프할 필요가 없다
        parsePath(path)
        parsePath2(path2)

        val priceString = """${'$'}99.9""" // 여러줄 문자열에선 이스케이프가 안되어서 '$' 문자를 넣고 싶으면 문자열 템플릿을 사용해야 한다.
        println(priceString)
    }
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePath2(path: String) {
    // 3중 따옴표 문자열에서는 역슬래시(\)를 포함한 어떤 문자도 이스케이프할 필요가 없다.
    // (.+)/ -> 마지막 슬래시까지 끊는것, 즉 디렉터리
    // (.+)\. -> 마지막 점(.), 즉 파일 이름 (3중 따옴표 문자열이 아니면 마침표 기호를 이스케이프하려면 \\. 써야하지만 여기선 \. 이렇게만 쓰면 된다)
    // (.+) -> 확장자
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire((path)) // 정규식에 매치시킨다.
    if (matchResult != null) { // 매치에 성공하면 결과가 null 이 아님
        val (directory, filename, extension) = matchResult.destructured // 그룹별로 분해한 매치 결과를 의미한 'destructured' 프로퍼티를 각 변수에 대입한다
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}
