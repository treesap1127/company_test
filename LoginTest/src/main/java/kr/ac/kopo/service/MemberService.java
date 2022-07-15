package kr.ac.kopo.service;

import kr.ac.kopo.model.User;

public interface MemberService {

	boolean checkId(String id);

	String signup(User item);

	User login(User item);

	String findpasswd(User id);

	User updatepass(User user);
	
}
