package kr.ac.kopo.dao;

import kr.ac.kopo.model.User;

public interface MemberDao {

	void signup(User item);

	boolean checkId(String id);

	User login(User item);

	String findpasswd(User user);

	boolean updatepass(User user);

}
