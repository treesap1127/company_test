package kr.ac.kopo.test;
import org.springframework.stereotype.Service;

@Service
public class TestServiceExtends extends TestService {//다중상속 불가

	@Override
	public void test1() {
		System.out.println("extends1");
	}
	@Override
	public void test4() {
		System.out.println("extends4");
	}
}
