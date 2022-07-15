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
		sql.insert("member.signup", item);
	}

	@Override
	public boolean checkId(String id) {
		int bool = sql.selectOne("member.checkid", id);// 중복 있으면 카운터 값으로 나옴!
		if (bool == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User login(User item) {
		User login = sql.selectOne("member.login", item);
		try {
			login.getName();// 여기서 null 값이 들어가면 바로 오류가 떠버린다..
		} catch (NullPointerException e) {
			item.setTel("1");
			return item;
		}
		return login;
	}

	@Override
	public String findpasswd(User user) {
		int bool = sql.selectOne("member.checkid", user);// 아이디가 있는지 확인
		if (bool == 1) {
			sql.update("member.findpass", user);//업데이트~
			return user.getPasswd();//임시 비밀번호 발송
		} else {
			return "";
		}
	}

	@Override
	public User updatepass(User user) {
		sql.update("member.updatepass", user);
		
		return sql.selectOne("member.login", user);
	}

}
