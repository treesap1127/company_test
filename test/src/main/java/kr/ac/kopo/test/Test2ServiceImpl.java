package kr.ac.kopo.test;

import org.springframework.stereotype.Service;

@Service
public class Test2ServiceImpl implements Test2Service /*,Test3Service*/ {//다중 구현 가능!

	@Override
	public void test() {
		System.out.println("implements1");
	}

/*	@Override
	public void testtest() {
		System.out.println("implements2");
		
	}*/



}