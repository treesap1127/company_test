package kr.ac.kopo.dao;

import java.util.List;

import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneExcel;
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
	
	int filecode();

	void deletefile(int code);

	List<OneFile> file(int code);

	void insertfile(OneExcel oneUser);

	List<Integer> filecodefind(int code);

	void deleteexcel(int filecodefind);

	List<OneExcel> excelfind(int filecode);

	OneFile onefile_fliecode(int filecode);

	List<Integer> filecount(int code);


}
