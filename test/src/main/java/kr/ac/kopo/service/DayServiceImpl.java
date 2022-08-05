package kr.ac.kopo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.dao.DayDao;
@Service
public class DayServiceImpl implements dayService {
	@Autowired
	DayDao dao;
	@Override
	public List<String> day_list() {
		return dao.day_list();
	}

}
