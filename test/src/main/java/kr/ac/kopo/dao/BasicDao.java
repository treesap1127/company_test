package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneFile;
import kr.ac.kopo.util.Pager;

public interface BasicDao {

	List<One> list(Pager pager);

	void add(One data);

	One item(int code);

	void delete(int code);

	void update(One data);

	void fileadd(OneFile filedata, One data);

	int fileitem();

	void deletefile(int code);

}
