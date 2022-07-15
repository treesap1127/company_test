package kr.ac.kopo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.MemberDao;
import kr.ac.kopo.model.User;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	@Override
	public boolean checkId(String id) {
		return dao.checkId(id);
	}

	@Override
	public String signup(User item) {
		String pass = pass();
		item.setPasswd(pass);
		dao.signup(item);
		System.out.println(pass);
		return pass;
	}

	@Override
	public User login(User item) {
		User pass = dao.login(item);
		pass.setPasswd(null);//password 유출하면 안되니까 모델에서 비우고 실행!
		return pass;
	}

	@Override
	public String findpasswd(User user) {
		String pass = pass();
		user.setPasswd(pass);
		return dao.findpasswd(user);
	}

	private static String pass() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거)
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	@Override
	public User updatepass(User user) {
		return dao.updatepass(user);
	}

}
