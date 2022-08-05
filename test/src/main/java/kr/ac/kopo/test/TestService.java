package kr.ac.kopo.test;

//서비스 달면 인스턴스 생성 불가
public abstract class TestService {

	public void test1() {}		//인터페이스와 다르게 메소드 형식을 지닌다.
	public void test2() {}// 서비스 상속 받은 곳에서 굳이 구현하지 않아도 괜찮다.
//	public abstract void test3(); // 서비스에서 구현을 꼭 해줘야한다!
	public void test4() {
		System.out.println("extends3");
	}

}
// 추상 클래스의 경우 추상 메소드는 반드시 구현해야 하는 경우 선언 해준다!
// 일반적으로 위와 같이 메소드를 사용하여 상속받아 사용하고 있다.
