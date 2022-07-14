package kr.ac.kopo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.User;
@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SqlSession sql;
	@Override
	public void signup(User item) {
		sql.insert("member.signup",item);
	}
	@Override
	public boolean checkId(String id) {
		System.out.println(id);
		int bool=sql.selectOne("member.checkid",id);
		System.out.println(bool);
		if(bool==0) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public User login(User item) {
		User login=sql.selectOne("member.login",item);
		try {
			login.getName();
		} catch (NullPointerException e) {
			item.setTel("1");
			return item;
		}
		return login;
	}
	@Override
	public String findpasswd(User user) {
		int bool=sql.selectOne("member.checkid",user);
		if(bool==1) {
			sql.update("member.updatepass", user);
			return user.getPasswd();
		}
		else {
			return "";
		}
	}
	@Override
	public boolean updatepass(User user) {
		try {
			sql.update("member.updatepass", user);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
