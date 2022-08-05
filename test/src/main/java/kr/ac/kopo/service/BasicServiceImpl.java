package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.dao.BasicDao;
import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneExcel;
import kr.ac.kopo.model.OneFile;
import kr.ac.kopo.model.User;
import kr.ac.kopo.util.Pager;
@Service
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicDao dao;
	@Override
	public List<One> list(Pager pager) {
		int total = dao.total(pager);
		pager.setTotal(total);
		return dao.list(pager);
	}

	@Override
	public void add(One data) {
		dao.add(data);
	}


	@Override
	public One item(int code) {
		return dao.item(code);
	}
	
	@Override
	public List<OneFile> updatefileitem(int code) {
		return dao.updatefileitem(code);
	}

	@Override
	@Transactional// 이 클래스를 상속해서 프록시 객체를 생성하는데 상속 불가. ++아무나 와서 바꾸면 안되니까
	public void delete(int code) {
		try {
			for(int i:dao.filecodefind(code)) {
				dao.deleteexcel(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.deletefile(code);
		dao.delete(code);
//		User u=new User();
//		u.id="test";
	}

	@Override
	@Transactional
	public void update(One data) {
		dao.update(data);
	}

	@Override
	public void fileadd(OneFile filedata,One data) {
		dao.fileadd(filedata,data);
	}

	@Override
	public int fileitem() {
		return dao.fileitem();
	}
	
	@Override
	public int filecode() {
		return dao.filecode();
	}

	@Override
	public List<OneFile> file(int code) {
		return dao.file(code);
	}

	@Override
	public void insertfile(OneExcel oneUser) {
		dao.insertfile(oneUser);
	}

	@Override
	public List<OneExcel> excelfind(int filecode) {
		return dao.excelfind(filecode);
	}

	@Override
	public OneFile onefile_fliecode(int filecode) {
		return dao.onefile_fliecode(filecode);
	}

	@Override
	@Transactional
	public void filedelete(int filecode) {
		dao.deleteexcel(filecode);
		dao.filedelte(filecode);
		
	}

}
