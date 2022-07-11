package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.dao.BasicDao;
import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneFile;
import kr.ac.kopo.util.Pager;
@Service
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicDao dao;
	@Override
	public List<One> list(Pager pager) {
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
	@Transactional
	public void delete(int code) {
		dao.deletefile(code);
		dao.delete(code);
	}

	@Override
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

}
