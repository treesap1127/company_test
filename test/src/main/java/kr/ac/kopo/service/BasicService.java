package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneExcel;
import kr.ac.kopo.model.OneFile;
import kr.ac.kopo.util.Pager;

public interface BasicService {
	List<One> list(Pager pager);
	
	void add(One data);

	One item(int code);

	void delete(int code);

	void update(One data);

	void fileadd(OneFile filedata, One data);

	int fileitem();
	
	int filecode();

	OneFile file(int code);

	void insertfile(OneExcel oneUser);

	List<OneExcel> excelfind(int filecode);

	OneFile onefile_fliecode(int filecode);



}
