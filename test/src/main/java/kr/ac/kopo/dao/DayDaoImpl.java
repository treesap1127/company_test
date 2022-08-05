package kr.ac.kopo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class DayDaoImpl implements DayDao {
	@Autowired
	SqlSession sql;
	@Override
	public List<String> day_list() {
		return sql.selectList("basic.daylist");
	}

}
