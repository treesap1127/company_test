package kr.ac.kopo.test;


public interface Test2Service {

	void test();
//	void testtest();
	public default void testtesttest() {//주석 해체 후 컨트롤러에서 명령
		System.out.println("test");
	}

}
//하위호환성과 유연성때문에 java 8버전 부터 생긴 defalut 인터페이스에 생성시 인터페이스 내부에서 구현이 가능하다.
//기존 인터페이스는 모든 구현 파일에 구현을 해주어야한다.
