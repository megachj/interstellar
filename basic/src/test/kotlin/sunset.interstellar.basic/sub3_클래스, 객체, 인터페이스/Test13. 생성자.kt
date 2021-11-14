package sunset.interstellar.basic.`sub3_클래스, 객체, 인터페이스`

class Test13

// 아래처럼 클래스 뒤에 괄호로 둘러싸인 코드를 주 생성자(primary constructor) 라고 부른다.
class User(val nickname: String)

// 위에 주 생성자를 풀어서 쓰면 아래와 같다.
// constructor 키워드: 주 생성자나 부 생성자 정의를 시작할 때 사용
// init 키워드: 초기화 블록을 시작할 때 사용, 클래스의 객체가 만들어질 때(인스턴스화될 때) 실행될 초기화 코드.
// init 은 주 생성자에서만 사용할 수 있고, 여러개 블록을 써도 된다.
class User2 constructor(_nickname: String) { // 파라미터가 하나만 있는 주 생성자
    val nickname: String

    init { // 초기화 블록
        nickname = _nickname
    }

    init {
        // ...
    }
}

// 아래 코드도 동일하게 주 생성자이다.
class User3(_nickname: String) {
    val nickname = _nickname
}

open class OpenUser(val nickname: String)

// 클래스에 기반 클래스가 있다면 주 생성자에서 기반 클래스의 생성자를 호출해야 한다.
class TwitterUser(nickname: String) : OpenUser(nickname)

open class Button1 // 인자가 없는 디폴트 생성자가 만들어진다.
class RadioButton : Button1()

// 주 생성자가 없는 클래스
open class View1 {
    constructor(ctx: String) { // 부 생성자
        // ...
    }

    constructor(ctx: String, attr: String) { // 부 생성자
        // ...
    }
}

class MyButton : View1 {
    constructor(ctx: String) : this(ctx, "") { // this() 를 통해 자신의 다른 생성자 호출
        // ...
    }

    constructor(ctx: String, attr: String) : super(ctx, attr) { // super 로 상위 클래스 생성자 호출
        // ...
    }
}
