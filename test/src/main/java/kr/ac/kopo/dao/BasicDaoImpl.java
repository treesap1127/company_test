package kr.ac.kopo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.model.One;
import kr.ac.kopo.model.OneExcel;
import kr.ac.kopo.model.OneFile;
import kr.ac.kopo.util.Pager;
@Repository
public class BasicDaoImpl implements BasicDao {
	@Autowired
	SqlSession sql;
	@Override
	public List<One> list(Pager pager) {
		return sql.selectList("basic.list",pager);
	}

	@Override
	public void add(One data) {
		sql.insert("basic.add", data);
	}

	@Override
	public One item(int code) {
		return sql.selectOne("basic.item",code);
	}

	@Override
	public void delete(int code) {
		sql.delete("basic.delete", code);
	}
	@Override
	public void deletefile(int code) {
		sql.delete("basic.deletefile", code);
	}
	@Override
	public void deleteexcel(int filecode) {
		sql.delete("basic.deleteexcel", filecode);
	}
	@Override
	public void update(One data) {
		sql.update("basic.update", data);
	}

	@Override
	public void fileadd(OneFile filedata,One data) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("filedata", filedata);
		map.put("data", data);
		sql.insert("basic.fileadd", map);
	}

	@Override
	public int fileitem() {
		return sql.selectOne("basic.filenum");
	}
	
	@Override
	public int filecode() {
		return sql.selectOne("basic.filecode");
	}
	@Override
	public int filecodefind(int code) {
		return sql.selectOne("basic.filecodefind",code);
	}
	@Override
	public List<OneFile> file(int code) {
		
		return sql.selectList("basic.file",code);
	}

	@Override
	public void insertfile(OneExcel oneUser) {
		sql.insert("basic.fileinsert", oneUser);
	}

	@Override
	public List<OneExcel> excelfind(int filecode) {
		return sql.selectList("basic.excelfind", filecode);
	}

	@Override
	public OneFile onefile_fliecode(int filecode) {
		return sql.selectOne("basic.onefileFliecode", filecode);
	}

	@Override
	public List<Integer> filecount(int code) {
		return sql.selectList("basic.filecount", code);
	}

}
